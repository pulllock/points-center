package fun.pullock.points.core.model.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(title = "可用积分详情")
@Data
public class DetailVO {

    public DetailVO() {
    }

    public DetailVO(Long available, List<PointsDetailVO> details) {
        this.available = available;
        this.details = details;
    }

    @Schema(title = "可用数量")
    private Long available;

    @Schema(title = "可用列表")
    private List<PointsDetailVO> details;
}
