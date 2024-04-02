package fun.pullock.points.core.model.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(title = "日志明细")
@Data
public class LogDetailVO {

    @Schema(title = "创建时间")
    private LocalDateTime createTime;

    @Schema(title = "过期时间")
    private LocalDateTime expireTime;

    @Schema(title = "总数量")
    private Long total;

    @Schema(title = "已使用数量")
    private Long used;

    @Schema(title = "当前使用数量")
    private Long currentUsed;

    @Schema(title = "业务描述")
    private String bizDescription;
}
