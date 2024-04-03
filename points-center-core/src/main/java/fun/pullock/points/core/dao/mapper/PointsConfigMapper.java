package fun.pullock.points.core.dao.mapper;

import fun.pullock.points.core.dao.model.PointsConfigDO;

import java.util.Arrays;
import java.util.List;

public interface PointsConfigMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PointsConfigDO row);

    int insertSelective(PointsConfigDO row);

    PointsConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PointsConfigDO row);

    int updateByPrimaryKey(PointsConfigDO row);

    List<PointsConfigDO> selectAll();
}