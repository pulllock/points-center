package fun.pullock.points.core.dao.mapper;

import fun.pullock.points.core.dao.model.UserPointsLogDO;

public interface UserPointsLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserPointsLogDO row);

    int insertSelective(UserPointsLogDO row);

    UserPointsLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPointsLogDO row);

    int updateByPrimaryKey(UserPointsLogDO row);
}