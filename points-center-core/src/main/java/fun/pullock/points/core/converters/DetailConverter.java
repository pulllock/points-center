package fun.pullock.points.core.converters;

import fun.pullock.points.core.dao.model.UserPointsDetailDO;
import fun.pullock.points.core.model.app.vo.PointsDetailVO;
import fun.pullock.points.core.model.dto.DetailDTO;

public class DetailConverter {

    private DetailConverter() {}

    public static PointsDetailVO toDetailVO(DetailDTO source) {
        if (source == null) {
            return null;
        }

        PointsDetailVO target = new PointsDetailVO();
        target.setCreateTime(source.getCreateTime());
        target.setExpireTime(source.getExpireTime());
        target.setTotal(source.getTotal());
        target.setUsed(source.getUsed());
        target.setAvailable(source.getTotal() - source.getUsed());
        target.setBizDescription(source.getBizDescription());
        return target;
    }

    public static DetailDTO toDetailDTO(UserPointsDetailDO source) {
        if (source == null) {
            return null;
        }

        DetailDTO target = new DetailDTO();
        target.setId(source.getId());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
        target.setUserId(source.getUserId());
        target.setConfigId(source.getConfigId());
        target.setChannelCode(source.getChannelCode());
        target.setTotal(source.getTotal());
        target.setUsed(source.getUsed());
        target.setSource(source.getSource());
        target.setUniqueSourceId(target.getUniqueSourceId());
        target.setBizId(source.getBizId());
        target.setBizDescription(source.getBizDescription());
        return target;
    }

    public static UserPointsDetailDO toDetailDO(DetailDTO source) {
        if (source == null) {
            return null;
        }
        UserPointsDetailDO target = new UserPointsDetailDO();
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(target.getUpdateTime());
        target.setUserId(source.getUserId());
        target.setConfigId(source.getConfigId());
        target.setChannelCode(source.getChannelCode());
        target.setExpireTime(source.getExpireTime());
        target.setTotal(source.getTotal());
        target.setSource(source.getSource());
        target.setUniqueSourceId(source.getUniqueSourceId());
        target.setBizId(source.getBizId());
        target.setBizDescription(source.getBizDescription());
        return target;
    }
}
