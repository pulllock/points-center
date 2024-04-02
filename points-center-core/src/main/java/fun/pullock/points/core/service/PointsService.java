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
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static fun.pullock.api.enums.ErrorCode.CONCURRENCY_ERROR;
import static fun.pullock.api.enums.ErrorCode.PARAM_ERROR;
import static fun.pullock.points.core.enums.ConfigStatus.ENABLE;
import static fun.pullock.points.core.enums.ConfigType.UN_LIMIT;
import static fun.pullock.points.core.enums.ExpirationRuleType.FIX;
import static fun.pullock.points.core.enums.ExpirationRuleType.RELATIVE;
import static fun.pullock.points.core.enums.LogType.GRANT;

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

    @Transactional
    public boolean grant(GrantParam param) {
        // 查询积分日志
        LogDTO log = logService.queryByUniqueKey(
                param.getUserId(), param.getSource(), param.getuniqueSourceId()
        );
        if (log != null) {
            throw new ServiceException(CONCURRENCY_ERROR, "重复请求");
        }

        // 插入积分日志
        log = createLog(param);

        // 校验渠道
        checkChannel(param);

        // 校验配置
        ConfigDTO config = checkConfig(param);

        // 增加积分明细
        LocalDateTime now = LocalDateTime.now();
        DetailDTO detail = createDetail(param, config, now);

        // 增加用户积分
        addUserPoints(param, now);

        // 更新积分日志中的详情
        updateLogDetail(detail, log);
        return true;
    }

    public boolean use(UseParam param) {
        // TODO
        return false;
    }

    public boolean rollback(RollbackParam param) {
        // TODO
        return false;
    }

    public boolean reclaim(ReclaimParam param) {
        // TODO
        return false;
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
        detail.setNumber(param.getNumber());
        detail.setSource(param.getSource());
        detail.setUniqueSourceId(param.getuniqueSourceId());
        detail.setBizId(param.getBizId());
        detail.setBizDescription(param.getBizDescription());

        detail.setExpireTime(calculateExpireTime(config, now));
        detailService.create(detail);
        return detail;
    }

    private LocalDateTime calculateExpireTime(ConfigDTO config, LocalDateTime now) {
        if (config.getType() == UN_LIMIT.getType()) {
            return null;
        }

        ExpirationRuleDTO rule = config.getExpirationRule();
        if (rule == null) {
            return null;
        }

        if (rule.getType() == RELATIVE.getType()) {
            return now.plusDays(rule.getDays());
        }

        else if (rule.getType() == FIX.getType()) {
            return rule.getExpirationTime();
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

    private void checkChannel(GrantParam param) {
        ChannelDTO channel = channelService.queryByCode(param.getChannelCode());
        if (channel == null) {
            throw new ServiceException(PARAM_ERROR, "渠道不存在");
        }
    }

    private LogDTO createLog(GrantParam param) {
        LogDTO log = new LogDTO();
        log.setCreateTime(LocalDateTime.now());
        log.setUpdateTime(log.getCreateTime());
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
