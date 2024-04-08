package fun.pullock.points.core.service;

import fun.pullock.points.api.enums.ErrorCode;
import fun.pullock.general.model.ServiceException;
import fun.pullock.points.core.converters.LogConverter;
import fun.pullock.points.core.dao.mapper.UserPointsLogMapper;
import fun.pullock.points.core.dao.model.UserPointsLogDO;
import fun.pullock.points.core.model.dto.LogDTO;
import jakarta.annotation.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static fun.pullock.points.core.converters.LogConverter.toLogDO;
import static fun.pullock.points.core.converters.LogConverter.toLogDTO;

@Service
public class LogService {

    @Resource
    private UserPointsLogMapper userPointsLogMapper;

    public LogDTO queryByUniqueKey(Long userId, String source, String uniqueSourceId) {
        return toLogDTO(userPointsLogMapper.selectByUniqueKey(userId, source, uniqueSourceId));
    }

    public void create(LogDTO log) {
        try {
            UserPointsLogDO logDO = toLogDO(log);
            userPointsLogMapper.insertSelective(logDO);
            log.setId(logDO.getId());
        } catch (DuplicateKeyException e) {
            throw new ServiceException(ErrorCode.CONCURRENCY_ERROR);
        }
    }

    public boolean updateDetail(Long id, String detail) {
        return userPointsLogMapper.updateDetail(id, detail) == 1;
    }

    public List<LogDTO> history(Long userId) {
        return userPointsLogMapper.selectAll(userId).stream().map(LogConverter::toLogDTO).collect(Collectors.toList());
    }
}
