package fun.pullock.points.core.model.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(title = "积分详情")
@Data
public class PointsDetailVO {

    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    @Schema(title = "过期时间")
    private LocalDateTime expireTime;

    @Schema(title = "总数量")
    private Long total;

    @Schema(title = "已使用数量")
    private Long used;

    @Schema(title = "可用数量")
    private Long available;

    @Schema(title = "业务描述")
    private String bizDescription;
}
