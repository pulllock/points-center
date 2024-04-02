package fun.pullock.points.core.dao.mapper;

import fun.pullock.points.core.dao.model.UserPointsLogDO;
import org.apache.ibatis.annotations.Param;

import java.util.Arrays;
import java.util.List;

public interface UserPointsLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserPointsLogDO row);

    int insertSelective(UserPointsLogDO row);

    UserPointsLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPointsLogDO row);

    int updateByPrimaryKey(UserPointsLogDO row);

    UserPointsLogDO selectByUniqueKey(
            @Param("userId") Long userId,
            @Param("source") String source,
            @Param("uniqueSourceId") String uniqueSourceId
    );

    boolean updateStatus(
            @Param("newStatus") int newStatus,
            @Param("oldStatus") int oldStatus,
            @Param("id") Long id
    );

    int updateDetail(@Param("id") Long id, @Param("detail") String detail);

    List<UserPointsLogDO> selectAll(@Param("userId") Long userId);
}