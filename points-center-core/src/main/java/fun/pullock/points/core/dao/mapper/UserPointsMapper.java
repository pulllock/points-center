package fun.pullock.points.core.dao.mapper;

import fun.pullock.points.core.dao.model.UserPointsDO;
import org.apache.ibatis.annotations.Param;

public interface UserPointsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserPointsDO row);

    int insertSelective(UserPointsDO row);

    UserPointsDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPointsDO row);

    int updateByPrimaryKey(UserPointsDO row);

    UserPointsDO selectByUserId(Long userId);

    void insertOrUpdate(UserPointsDO points);

    int grant(@Param("id") Long id, @Param("number") Long number);
}