package fun.pullock.points.core.converters;

import fun.pullock.points.api.model.result.ExpirationRule;
import fun.pullock.points.api.model.result.PointsConfig;
import fun.pullock.points.core.dao.model.PointsConfigDO;
import fun.pullock.points.core.model.dto.ConfigDTO;
import fun.pullock.points.core.model.dto.ExpirationRuleDTO;
import fun.pullock.starter.json.Json;

public class ConfigConverter {

    private ConfigConverter() {}

    public static ConfigDTO toConfigDTO(PointsConfigDO source) {
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

    public static PointsConfig toPointsConfig(ConfigDTO source) {
        if (source == null) {
            return null;
        }

        PointsConfig target = new PointsConfig();
        target.setId(source.getId());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setChannelCode(source.getChannelCode());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setStatus(source.getStatus());
        target.setType(source.getType());
        target.setStock(source.getStock());
        target.setExpirationRule(toExpirationRule(source.getExpirationRule()));
        return target;
    }

    public static ExpirationRule toExpirationRule(ExpirationRuleDTO source) {
        if (source == null) {
            return null;
        }

        ExpirationRule target = new ExpirationRule();
        target.setType(source.getType());
        target.setDays(source.getDays());
        target.setExpirationTime(source.getExpirationTime());
        return target;
    }
}
