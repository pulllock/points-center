package fun.pullock.points.core.dao.mapper;

import fun.pullock.points.core.dao.model.UserPointsDetailDO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public interface UserPointsDetailMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserPointsDetailDO row);

    int insertSelective(UserPointsDetailDO row);

    UserPointsDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPointsDetailDO row);

    int updateByPrimaryKey(UserPointsDetailDO row);

    List<UserPointsDetailDO> selectExpiring(@Param("userId") Long userId, @Param("now") LocalDateTime now);

    List<UserPointsDetailDO> selectUnlimited(Long userId);

    int use(@Param("id") Long id, @Param("used") Long used);

    void deleteByIds(@Param("ids") List<Long> ids);
}