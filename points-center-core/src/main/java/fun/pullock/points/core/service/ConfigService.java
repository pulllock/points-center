package fun.pullock.points.core.service;

import fun.pullock.points.core.dao.mapper.PointsConfigMapper;
import fun.pullock.points.core.dao.model.PointsConfigDO;
import fun.pullock.points.core.model.dto.ConfigDTO;
import fun.pullock.points.core.model.dto.ExpirationRuleDTO;
import fun.pullock.starter.json.Json;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    @Resource
    private PointsConfigMapper pointsConfigMapper;

    public ConfigDTO queryById(Long id) {
        return toConfigDTO(pointsConfigMapper.selectByPrimaryKey(id));
    }

    private ConfigDTO toConfigDTO(PointsConfigDO source) {
        if (source == null) {
            return null;
        }

        ConfigDTO target = new ConfigDTO();
        target.setId(source.getId());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setChannelCode(source.getChannelCode());
        target.setStatus(source.getStatus());
        target.setType(source.getType());
        target.setStock(source.getStock());
        target.setExpirationRule(Json.toObject(source.getExpirationRule(), ExpirationRuleDTO.class));
        return target;
    }
}
