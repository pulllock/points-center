package fun.pullock.api.client;

import fun.pullock.api.model.param.GrantParam;
import fun.pullock.api.model.param.ReclaimParam;
import fun.pullock.api.model.param.RollbackParam;
import fun.pullock.api.model.param.UseParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface PointsClient {

    /**
     * 发放积分
     * @param param 发放积分参数
     * @return 发放结果
     */
    @PostMapping(value = "/rpc/points/grant", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    boolean grant(@RequestBody GrantParam param);

    /**
     * 使用积分
     * @param param 使用积分参数
     * @return 使用结果
     */
    @PostMapping(value = "/rpc/points/use", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    boolean use(@RequestBody UseParam param);

    /**
     * 回退积分
     * @param param 回退积分参数
     * @return 回退结果
     */
    @PostMapping(value = "/rpc/points/rollback", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    boolean rollback(@RequestBody RollbackParam param);

    /**
     * 回收积分
     * @param param 回收积分参数
     * @return 回收结果
     */
    @PostMapping(value = "/rpc/points/reclaim", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    boolean reclaim(@RequestBody ReclaimParam param);
}
