package fun.pullock.points.core.model.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "积分汇总")
@Data
public class SummaryVO {

    @Schema(title = "总数量")
    private Long total;

    @Schema(title = "已使用数量")
    private Long used;

    @Schema(title = "已过期数量")
    private Long expired;

    @Schema(title = "可用数量")
    private Long available;
}
