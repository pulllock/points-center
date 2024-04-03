package fun.pullock.points.core.converters;

import fun.pullock.points.core.dao.model.UserPointsLogDO;
import fun.pullock.points.core.model.app.vo.LogDetailVO;
import fun.pullock.points.core.model.app.vo.LogVO;
import fun.pullock.points.core.model.dto.DetailDTO;
import fun.pullock.points.core.model.dto.LogDTO;
import fun.pullock.points.core.model.dto.LogDetailDTO;
import fun.pullock.starter.json.Json;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

public class LogConverter {

    private LogConverter() {}

    public static LogVO toLogVO(LogDTO source) {
        if (source == null) {
            return null;
        }

        LogVO target = new LogVO();
        target.setCreateTime(source.getCreateTime());
        target.setType(source.getType());
        target.setNumber(source.getNumber());
        target.setBizDescription(source.getBizDescription());
        target.setDetails(source.getDetail().stream().map(LogConverter::toLogDetailVO).collect(Collectors.toList()));
        return target;
    }

    public static LogDetailVO toLogDetailVO(LogDetailDTO source) {
        if (source == null) {
            return null;
        }

        LogDetailVO target = new LogDetailVO();
        target.setCreateTime(source.getCreateTime());
        target.setExpireTime(source.getExpireTime());
        target.setTotal(source.getTotal());
        target.setUsed(source.getUsed());
        target.setCurrentUsed(source.getCurrentUsed());
        target.setBizDescription(source.getBizDescription());
        return target;
    }

    public static LogDTO toLogDTO(UserPointsLogDO source) {
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
        target.setDetail(Json.toArray(source.getDetail(), LogDetailDTO.class));
        return target;
    }

    public static LogDetailDTO toLogDetailDTO(DetailDTO source) {
        if (source == null) {
            return null;
        }

        LogDetailDTO target = new LogDetailDTO();
        BeanUtils.copyProperties(source, target);
        target.setUsed(source.getTotal());
        target.setCurrentUsed(source.getTotal() - source.getUsed());
        return target;
    }

    public static UserPointsLogDO toLogDO(LogDTO source) {
        if (source == null) {
            return null;
        }

        UserPointsLogDO target = new UserPointsLogDO();
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
        target.setDetail(Json.toJson(source.getDetail()));
        return target;
    }
}
