package fun.pullock.points.core.service;

import fun.pullock.api.enums.ErrorCode;
import fun.pullock.general.model.ServiceException;
import fun.pullock.points.core.converters.DetailConverter;
import fun.pullock.points.core.dao.mapper.UserPointsDetailMapper;
import fun.pullock.points.core.dao.model.UserPointsDetailDO;
import fun.pullock.points.core.model.dto.DetailDTO;
import fun.pullock.points.core.model.dto.LogDetailDTO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static fun.pullock.points.core.converters.DetailConverter.toDetailDO;
import static fun.pullock.points.core.converters.DetailConverter.toDetailDTO;

@Service
public class DetailService {

    @Resource
    private UserPointsDetailMapper userPointsDetailMapper;

    public boolean create(DetailDTO detail) {
        try {
            UserPointsDetailDO detailDO = toDetailDO(detail);
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
                .map(DetailConverter::toDetailDTO)
                .collect(Collectors.toList());
    }

    public List<DetailDTO> queryUnlimited(Long userId) {
        return userPointsDetailMapper.selectUnlimited(userId)
                .stream()
                .map(DetailConverter::toDetailDTO)
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
                .map(DetailConverter::toDetailDTO)
                .collect(Collectors.toList());
    }
}
