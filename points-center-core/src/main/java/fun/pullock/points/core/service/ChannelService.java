package fun.pullock.points.core.service;

import fun.pullock.points.core.dao.mapper.ChannelMapper;
import fun.pullock.points.core.dao.model.ChannelDO;
import fun.pullock.points.core.model.dto.ChannelDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {

    @Resource
    private ChannelMapper channelMapper;

    public ChannelDTO queryByCode(String code) {
        return toChannelDTO(channelMapper.selectByCode(code));
    }

    private ChannelDTO toChannelDTO(ChannelDO source) {
        if (source == null) {
            return null;
        }

        ChannelDTO target = new ChannelDTO();
        target.setId(source.getId());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setCode(source.getCode());
        target.setName(source.getName());
        return target;
    }
}
