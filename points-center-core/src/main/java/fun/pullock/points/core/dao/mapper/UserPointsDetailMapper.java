package fun.pullock.points.core.dao.mapper;

import fun.pullock.points.core.dao.model.UserPointsDetailDO;

public interface UserPointsDetailMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserPointsDetailDO row);

    int insertSelective(UserPointsDetailDO row);

    UserPointsDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPointsDetailDO row);

    int updateByPrimaryKey(UserPointsDetailDO row);
}