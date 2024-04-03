package fun.pullock.points.core.service;

import fun.pullock.points.core.converters.ConfigConverter;
import fun.pullock.points.core.dao.mapper.PointsConfigMapper;
import fun.pullock.points.core.model.dto.ConfigDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static fun.pullock.points.core.converters.ConfigConverter.toConfigDTO;

@Service
public class ConfigService {

    @Resource
    private PointsConfigMapper pointsConfigMapper;

    public ConfigDTO queryById(Long id) {
        return toConfigDTO(pointsConfigMapper.selectByPrimaryKey(id));
    }

    public List<ConfigDTO> configs() {
        return pointsConfigMapper.selectAll().stream().map(ConfigConverter::toConfigDTO).collect(Collectors.toList());
    }
}
