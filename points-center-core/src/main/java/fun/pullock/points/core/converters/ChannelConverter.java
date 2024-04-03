package fun.pullock.points.core.converters;

import fun.pullock.points.core.dao.model.ChannelDO;
import fun.pullock.points.core.model.dto.ChannelDTO;

public class ChannelConverter {

    private ChannelConverter() {}

    public static ChannelDTO toChannelDTO(ChannelDO source) {
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
