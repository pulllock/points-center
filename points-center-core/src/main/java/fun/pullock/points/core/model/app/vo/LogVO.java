package fun.pullock.points.core.model.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(title = "积分日志")
@Data
public class LogVO {

    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    @Schema(title = "类型，取值：1-发放 2-使用 3-回退 4-回收")
    private Integer type;

    @Schema(title = "数量")
    private Long number;

    @Schema(title = "业务描述")
    private String bizDescription;

    @Schema(title = "日志列表")
    private List<LogDetailVO> details;
}
