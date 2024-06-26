package fun.pullock.points.core.service;

import fun.pullock.points.core.dao.mapper.ChannelMapper;
import fun.pullock.points.core.model.dto.ChannelDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import static fun.pullock.points.core.converters.ChannelConverter.toChannelDTO;

@Service
public class ChannelService {

    @Resource
    private ChannelMapper channelMapper;

    public ChannelDTO queryByCode(String code) {
        return toChannelDTO(channelMapper.selectByCode(code));
    }
}
