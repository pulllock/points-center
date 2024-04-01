package fun.pullock.points.core.dao.mapper;

import fun.pullock.points.core.dao.model.ChannelDO;

public interface ChannelMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ChannelDO row);

    int insertSelective(ChannelDO row);

    ChannelDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelDO row);

    int updateByPrimaryKey(ChannelDO row);
}