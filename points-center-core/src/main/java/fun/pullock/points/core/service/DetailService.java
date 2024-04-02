package fun.pullock.points.core.service;

import fun.pullock.api.enums.ErrorCode;
import fun.pullock.general.model.ServiceException;
import fun.pullock.points.core.dao.mapper.UserPointsDetailMapper;
import fun.pullock.points.core.dao.model.UserPointsDetailDO;
import fun.pullock.points.core.model.app.vo.PointsDetailVO;
import fun.pullock.points.core.model.dto.DetailDTO;
import fun.pullock.points.core.model.dto.LogDetailDTO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
            detailDO.setTotal(detail.getTotal());
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

    public List<DetailDTO> queryExpiring(Long userId, LocalDateTime now) {
        return userPointsDetailMapper.selectExpiring(userId, now)
                .stream()
                .map(this::toDetailDTO)
                .collect(Collectors.toList());
    }

    public List<DetailDTO> queryUnlimited(Long userId) {
        return userPointsDetailMapper.selectUnlimited(userId)
                .stream()
                .map(this::toDetailDTO)
                .collect(Collectors.toList());
    }

    public boolean use(Long id, Long used) {
        return userPointsDetailMapper.use(id, used) == 1;
    }

    public void deleteByIds(List<Long> ids) {
        userPointsDetailMapper.deleteByIds(ids);
    }

    public DetailDTO queryById(Long id) {
        return toDetailDTO(userPointsDetailMapper.selectByPrimaryKey(id));
    }

    public boolean rollback(LogDetailDTO logDetail) {
        UserPointsDetailDO detailDO = new UserPointsDetailDO();
        BeanUtils.copyProperties(logDetail, detailDO);
        return userPointsDetailMapper.insert(detailDO) == 1;
    }

    public boolean rollback(DetailDTO detail) {
        UserPointsDetailDO detailDO = new UserPointsDetailDO();
        BeanUtils.copyProperties(detail, detailDO);
        return userPointsDetailMapper.updateByPrimaryKey(detailDO) == 1;
    }

    public List<DetailDTO> queryDetail(Long userId) {
        return userPointsDetailMapper.selectAll(userId)
                .stream()
                .map(this::toDetailDTO)
                .collect(Collectors.toList());
    }

    private DetailDTO toDetailDTO(UserPointsDetailDO source) {
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
}
