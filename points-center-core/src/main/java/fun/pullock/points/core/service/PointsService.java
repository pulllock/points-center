package fun.pullock.points.core.service;

import fun.pullock.api.model.param.GrantParam;
import fun.pullock.api.model.param.ReclaimParam;
import fun.pullock.api.model.param.RollbackParam;
import fun.pullock.api.model.param.UseParam;
import fun.pullock.general.model.ServiceException;
import fun.pullock.points.core.dao.mapper.UserPointsMapper;
import fun.pullock.points.core.dao.model.UserPointsDO;
import fun.pullock.points.core.model.dto.*;
import fun.pullock.starter.json.Json;
import fun.pullock.starter.redis.lock.RedisLock;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static fun.pullock.api.enums.ErrorCode.CONCURRENCY_ERROR;
import static fun.pullock.api.enums.ErrorCode.PARAM_ERROR;
import static fun.pullock.points.core.enums.ConfigStatus.ENABLE;
import static fun.pullock.points.core.enums.ConfigType.UNLIMITED;
import static fun.pullock.points.core.enums.ExpirationRuleType.FIX;
import static fun.pullock.points.core.enums.ExpirationRuleType.RELATIVE;
import static fun.pullock.points.core.enums.LogType.GRANT;
import static fun.pullock.points.core.enums.LogType.USE;

@Service
public class PointsService {

    @Resource
    private LogService logService;

    @Resource
    private ChannelService channelService;

    @Resource
    private ConfigService configService;

    @Resource
    private DetailService detailService;

    @Resource
    private UserPointsMapper userPointsMapper;

    @Resource
    private RedisLock redisLock;

    @Transactional
    public boolean grant(GrantParam param) {
        // 查询积分日志
        LogDTO log = logService.queryByUniqueKey(
                param.getUserId(), param.getSource(), param.getuniqueSourceId()
        );
        if (log != null) {
            throw new ServiceException(CONCURRENCY_ERROR, "重复请求");
        }

        LocalDateTime now = LocalDateTime.now();

        // 插入积分日志
        log = createLog(param, now);

        // 校验渠道
        checkChannel(param.getChannelCode());

        // 校验配置
        ConfigDTO config = checkConfig(param);

        // 增加积分明细
        DetailDTO detail = createDetail(param, config, now);

        // 增加用户积分
        addUserPoints(param, now);

        // 更新积分日志中的详情
        updateLogDetail(detail, log);
        return true;
    }

    @Transactional
    public boolean use(UseParam param) {
        // 查询积分日志
        LogDTO log = logService.queryByUniqueKey(
                param.getUserId(), param.getSource(), param.getuniqueSourceId()
        );
        if (log != null) {
            throw new ServiceException(CONCURRENCY_ERROR, "重复请求");
        }

        LocalDateTime now = LocalDateTime.now();

        // 插入积分日志
        log = createLog(param, now);

        // 校验渠道
        checkChannel(param.getChannelCode());

        String lockKey = String.format("PointsUse::%s", param.getUserId());
        redisLock.lock(lockKey, 60 * 1000);
        try {
            // 查询带有有效期的积分记录
            List<DetailDTO> expiring = detailService.queryExpiring(param.getUserId(), now);

            // 过滤掉没有可用积分的记录
            expiring = expiring.stream()
                    .filter(d -> !Objects.equals(d.getTotal(), d.getUsed()))
                    .collect(Collectors.toList());

            List<DetailDTO> using = new ArrayList<>();
            Long total = 0L;
            for (DetailDTO detail : expiring) {
                total = total + (detail.getTotal() - detail.getUsed());
                using.add(detail);
                if (total >= param.getNumber()) {
                    break;
                }
            }

            // 即将过期的可用积分不足，继续查询永久积分
            if (total < param.getNumber()) {
                // 查询永久积分
                List<DetailDTO> unLimited = detailService.queryUnlimited(param.getUserId());

                // 过滤掉没有可用积分的记录
                unLimited = unLimited.stream()
                        .filter(d -> !Objects.equals(d.getTotal(), d.getUsed()))
                        .collect(Collectors.toList());

                for (DetailDTO detail : unLimited) {
                    total = total + (detail.getTotal() - detail.getUsed());
                    using.add(detail);
                    if (total >= param.getNumber()) {
                        break;
                    }
                }
            }

            DetailDTO split = null;

            // 可用积分不足
            if (total < param.getNumber()) {
                throw new ServiceException(PARAM_ERROR);
            }
            // 刚好等于要使用的积分
            else if (total == param.getNumber()) {
                // do nothing
            }
            // 大于要使用的积分
            else {
                split = using.remove(using.size() - 1);
            }

            // 删除这些记录
            if (CollectionUtils.isNotEmpty(using)) {
                detailService.deleteByIds(using.stream().map(DetailDTO::getId).collect(Collectors.toList()));
            }

            // 拆分最后一条多的积分
            if (split != null) {
                Long used = (split.getTotal() - split.getUsed()) - (total - param.getNumber());
                detailService.use(split.getId(), used);
                split.setUsed(split.getUsed() + used);
                split.setUpdateTime(now);
                using.add(split);
            }

            // 更新用户积分
            userPointsMapper.use(param.getUserId(), param.getNumber());

            // 更新日志
            updateLogDetail(using, log);
        } finally {
            redisLock.unlock(lockKey);
        }
        return true;
    }

    public boolean rollback(RollbackParam param) {
        // TODO
        return false;
    }

    public boolean reclaim(ReclaimParam param) {
        // TODO
        return false;
    }

    private void updateLogDetail(List<DetailDTO> using, LogDTO log) {
        List<DetailDTO> details = log.getDetail();
        if (details == null) {
            details = new ArrayList<>();
        }

        details.addAll(using);
        logService.updateDetail(log.getId(), Json.toJson(details));
    }

    private LogDTO createLog(UseParam param, LocalDateTime now) {
        LogDTO log = new LogDTO();
        log.setCreateTime(now);
        log.setUpdateTime(now);
        log.setUserId(param.getUserId());
        log.setConfigId(0L);
        log.setChannelCode(param.getChannelCode());
        log.setType(USE.getType());
        log.setNumber(param.getNumber());
        log.setSource(param.getSource());
        log.setUniqueSourceId(param.getuniqueSourceId());
        log.setBizId(param.getBizId());
        log.setBizDescription(param.getBizDescription());
        logService.create(log);
        return log;
    }

    private void updateLogDetail(DetailDTO detail, LogDTO log) {
        List<DetailDTO> details = new ArrayList<>();
        details.add(detail);
        logService.updateDetail(log.getId(), Json.toJson(details));
    }

    private void addUserPoints(GrantParam param, LocalDateTime now) {
        UserPointsDO points = userPointsMapper.selectByUserId(param.getUserId());
        if (points == null) {
            points = new UserPointsDO();
            points.setCreateTime(now);
            points.setUpdateTime(now);
            points.setUserId(param.getUserId());
            points.setTotal(param.getNumber());
            points.setUsed(0L);
            points.setExpired(0L);
            userPointsMapper.insertOrUpdate(points);
        } else {
            // 增加用户积分
            userPointsMapper.grant(points.getId(), param.getNumber());
        }
    }

    private DetailDTO createDetail(GrantParam param, ConfigDTO config, LocalDateTime now) {
        DetailDTO detail = new DetailDTO();
        detail.setCreateTime(now);
        detail.setUpdateTime(now);
        detail.setUserId(param.getUserId());
        detail.setConfigId(param.getConfigId());
        detail.setChannelCode(param.getChannelCode());
        detail.setTotal(param.getNumber());
        detail.setSource(param.getSource());
        detail.setUniqueSourceId(param.getuniqueSourceId());
        detail.setBizId(param.getBizId());
        detail.setBizDescription(param.getBizDescription());

        detail.setExpireTime(calculateExpireTime(config, now));
        detailService.create(detail);
        return detail;
    }

    private LocalDateTime calculateExpireTime(ConfigDTO config, LocalDateTime now) {
        if (config.getType() == UNLIMITED.getType()) {
            return null;
        }

        ExpirationRuleDTO rule = config.getExpirationRule();
        if (rule == null) {
            return null;
        }

        if (rule.getType() == RELATIVE.getType()) {
            return now.plusDays(rule.getDays()).withHour(23).withMinute(59).withSecond(59);
        }

        else if (rule.getType() == FIX.getType()) {
            return rule.getExpirationTime().withHour(23).withMinute(59).withSecond(59);
        }

        return null;
    }

    private ConfigDTO checkConfig(GrantParam param) {
        ConfigDTO config = configService.queryById(param.getConfigId());
        if (config == null) {
            throw new ServiceException(PARAM_ERROR, "配置不存在");
        }

        if (config.getStatus() != ENABLE.getStatus()) {
            throw new ServiceException(PARAM_ERROR, "配置状态错误");
        }

        // 现在实现中一个配置只支持一个渠道
        if (!param.getChannelCode().equals(config.getChannelCode())) {
            throw new ServiceException(PARAM_ERROR, "配置渠道错误");
        }

        return config;
    }

    private void checkChannel(String channelCode) {
        ChannelDTO channel = channelService.queryByCode(channelCode);
        if (channel == null) {
            throw new ServiceException(PARAM_ERROR, "渠道不存在");
        }
    }

    private LogDTO createLog(GrantParam param, LocalDateTime now) {
        LogDTO log = new LogDTO();
        log.setCreateTime(now);
        log.setUpdateTime(now);
        log.setUserId(param.getUserId());
        log.setConfigId(param.getConfigId());
        log.setChannelCode(param.getChannelCode());
        log.setType(GRANT.getType());
        log.setNumber(param.getNumber());
        log.setSource(param.getSource());
        log.setUniqueSourceId(param.getuniqueSourceId());
        log.setBizId(param.getBizId());
        log.setBizDescription(param.getBizDescription());
        logService.create(log);
        return log;
    }
}
