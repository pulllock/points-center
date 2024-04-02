package fun.pullock.points.core.service;

import fun.pullock.api.enums.ErrorCode;
import fun.pullock.general.model.ServiceException;
import fun.pullock.points.core.dao.mapper.UserPointsDetailMapper;
import fun.pullock.points.core.dao.model.UserPointsDetailDO;
import fun.pullock.points.core.model.dto.DetailDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

@Service
public class DetailService {

    @Resource
    private UserPointsDetailMapper userPointsDetailMapper;

    public boolean create(DetailDTO detail) {
        try {
            UserPointsDetailDO detailDO = new UserPointsDetailDO();
            detailDO.setCreateTime(detail.getCreateTime());
            detailDO.setUpdateTime(detailDO.getUpdateTime());
            detailDO.setUserId(detail.getUserId());
            detailDO.setConfigId(detail.getConfigId());
            detailDO.setChannelCode(detail.getChannelCode());
            detailDO.setExpireTime(detail.getExpireTime());
            detailDO.setNumber(detail.getNumber());
            detailDO.setSource(detail.getSource());
            detailDO.setUniqueSourceId(detail.getUniqueSourceId());
            detailDO.setBizId(detail.getBizId());
            detailDO.setBizDescription(detail.getBizDescription());

            int result = userPointsDetailMapper.insertSelective(detailDO);
            detail.setId(detailDO.getId());
            return result == 1;
        } catch (DuplicateKeyException e) {
            throw new ServiceException(ErrorCode.CONCURRENCY_ERROR, "重复请求");
        }
    }
}
