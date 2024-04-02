package fun.pullock.points.core.service;

import fun.pullock.api.enums.ErrorCode;
import fun.pullock.general.model.ServiceException;
import fun.pullock.points.core.dao.mapper.UserPointsLogMapper;
import fun.pullock.points.core.dao.model.UserPointsLogDO;
import fun.pullock.points.core.model.dto.DetailDTO;
import fun.pullock.points.core.model.dto.LogDTO;
import fun.pullock.points.core.model.dto.LogDetailDTO;
import fun.pullock.starter.json.Json;
import jakarta.annotation.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Resource
    private UserPointsLogMapper userPointsLogMapper;

    public LogDTO queryByUniqueKey(Long userId, String source, String uniqueSourceId) {
        return toUserPointsLogDTO(userPointsLogMapper.selectByUniqueKey(userId, source, uniqueSourceId));
    }

    public void create(LogDTO log) {
        try {
            UserPointsLogDO logDO = new UserPointsLogDO();
            logDO.setId(log.getId());
            logDO.setCreateTime(log.getCreateTime());
            logDO.setUpdateTime(log.getUpdateTime());
            logDO.setUserId(log.getUserId());
            logDO.setConfigId(log.getConfigId());
            logDO.setChannelCode(log.getChannelCode());
            logDO.setType(log.getType());
            logDO.setNumber(log.getNumber());
            logDO.setSource(log.getSource());
            logDO.setUniqueSourceId(log.getUniqueSourceId());
            logDO.setBizId(log.getBizId());
            logDO.setBizDescription(log.getBizDescription());
            logDO.setStatus(log.getStatus());
            logDO.setDetail(Json.toJson(log.getDetail()));
            userPointsLogMapper.insertSelective(logDO);
            log.setId(logDO.getId());
        } catch (DuplicateKeyException e) {
            throw new ServiceException(ErrorCode.CONCURRENCY_ERROR);
        }
    }

    public boolean updateStatus(int newStatus, int oldStatus, Long id) {
        return userPointsLogMapper.updateStatus(newStatus, oldStatus, id);
    }

    private LogDTO toUserPointsLogDTO(UserPointsLogDO source) {
        if (source == null) {
            return null;
        }

        LogDTO target = new LogDTO();
        target.setId(source.getId());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setUserId(source.getUserId());
        target.setConfigId(source.getConfigId());
        target.setChannelCode(source.getChannelCode());
        target.setType(source.getType());
        target.setNumber(source.getNumber());
        target.setSource(source.getSource());
        target.setUniqueSourceId(source.getUniqueSourceId());
        target.setBizId(source.getBizId());
        target.setBizDescription(source.getBizDescription());
        target.setStatus(source.getStatus());
        target.setDetail(Json.toArray(source.getDetail(), LogDetailDTO.class));
        return target;
    }

    public boolean updateDetail(Long id, String detail) {
        return userPointsLogMapper.updateDetail(id, detail) == 1;
    }
}
