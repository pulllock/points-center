package fun.pullock.points.core.controller.client;

import fun.pullock.api.client.PointsClient;
import fun.pullock.api.model.param.GrantParam;
import fun.pullock.api.model.param.ReclaimParam;
import fun.pullock.api.model.param.RollbackParam;
import fun.pullock.api.model.param.UseParam;
import fun.pullock.general.model.ServiceException;
import fun.pullock.points.core.service.PointsService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import static fun.pullock.api.enums.ErrorCode.PARAM_ERROR;

@RestController
public class PointsClientController implements PointsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointsClientController.class);

    @Resource
    private PointsService pointsService;

    @Override
    public boolean grant(GrantParam param) {
        if (param == null) {
            throw new ServiceException(PARAM_ERROR);
        }

        if (param.getUserId() == null) {
            throw new ServiceException(PARAM_ERROR, "userId不能为空");
        }

        if (param.getConfigId() == null) {
            throw new ServiceException(PARAM_ERROR, "configId不能为空");
        }

        if (StringUtils.isEmpty(param.getChannelCode())) {
            throw new ServiceException(PARAM_ERROR, "channelCode不能为空");
        }

        if (param.getNumber() == null || param.getNumber() <= 0) {
            throw new ServiceException(PARAM_ERROR, "number错误");
        }

        if (StringUtils.isEmpty(param.getSource())) {
            throw new ServiceException(PARAM_ERROR, "source不能为空");
        }

        if (StringUtils.isEmpty(param.getUniqueSourceId())) {
            throw new ServiceException(PARAM_ERROR, "uniqueSourceId不能为空");
        }

        if (param.getBizId() == null) {
            throw new ServiceException(PARAM_ERROR, "bizId不能为空");
        }

        if (StringUtils.isEmpty(param.getBizDescription())) {
            throw new ServiceException(PARAM_ERROR, "bizDescription不能为空");
        }

        return pointsService.grant(param);
    }

    @Override
    public boolean use(UseParam param) {
        if (param == null) {
            throw new ServiceException(PARAM_ERROR);
        }

        if (param.getUserId() == null) {
            throw new ServiceException(PARAM_ERROR, "userId不能为空");
        }

        if (StringUtils.isEmpty(param.getChannelCode())) {
            throw new ServiceException(PARAM_ERROR, "channelCode不能为空");
        }

        if (param.getNumber() == null || param.getNumber() <= 0) {
            throw new ServiceException(PARAM_ERROR, "number错误");
        }

        if (StringUtils.isEmpty(param.getSource())) {
            throw new ServiceException(PARAM_ERROR, "source不能为空");
        }

        if (StringUtils.isEmpty(param.getUniqueSourceId())) {
            throw new ServiceException(PARAM_ERROR, "uniqueSourceId不能为空");
        }

        if (param.getBizId() == null) {
            throw new ServiceException(PARAM_ERROR, "bizId不能为空");
        }

        if (StringUtils.isEmpty(param.getBizDescription())) {
            throw new ServiceException(PARAM_ERROR, "bizDescription不能为空");
        }

        return pointsService.use(param);
    }

    @Override
    public boolean rollback(RollbackParam param) {
        if (param == null) {
            throw new ServiceException(PARAM_ERROR);
        }

        if (param.getUserId() == null) {
            throw new ServiceException(PARAM_ERROR, "userId不能为空");
        }


        if (StringUtils.isEmpty(param.getChannelCode())) {
            throw new ServiceException(PARAM_ERROR, "channelCode不能为空");
        }


        if (StringUtils.isEmpty(param.getSource())) {
            throw new ServiceException(PARAM_ERROR, "source不能为空");
        }

        if (StringUtils.isEmpty(param.getUniqueSourceId())) {
            throw new ServiceException(PARAM_ERROR, "uniqueSourceId不能为空");
        }

        if (StringUtils.isEmpty(param.getOriginUniqueSourceId())) {
            throw new ServiceException(PARAM_ERROR, "originUniqueSourceId不能为空");
        }

        if (param.getBizId() == null) {
            throw new ServiceException(PARAM_ERROR, "bizId不能为空");
        }

        if (StringUtils.isEmpty(param.getBizDescription())) {
            throw new ServiceException(PARAM_ERROR, "bizDescription不能为空");
        }

        return pointsService.rollback(param);
    }

    @Override
    public boolean reclaim(ReclaimParam param) {
        if (param == null) {
            throw new ServiceException(PARAM_ERROR);
        }

        if (param.getUserId() == null) {
            throw new ServiceException(PARAM_ERROR, "userId不能为空");
        }

        if (StringUtils.isEmpty(param.getChannelCode())) {
            throw new ServiceException(PARAM_ERROR, "channelCode不能为空");
        }

        if (param.getNumber() == null || param.getNumber() <= 0) {
            throw new ServiceException(PARAM_ERROR, "number错误");
        }

        if (StringUtils.isEmpty(param.getSource())) {
            throw new ServiceException(PARAM_ERROR, "source不能为空");
        }

        if (StringUtils.isEmpty(param.getUniqueSourceId())) {
            throw new ServiceException(PARAM_ERROR, "uniqueSourceId不能为空");
        }

        if (param.getBizId() == null) {
            throw new ServiceException(PARAM_ERROR, "bizId不能为空");
        }

        if (StringUtils.isEmpty(param.getBizDescription())) {
            throw new ServiceException(PARAM_ERROR, "bizDescription不能为空");
        }

        return pointsService.reclaim(param);
    }
}
