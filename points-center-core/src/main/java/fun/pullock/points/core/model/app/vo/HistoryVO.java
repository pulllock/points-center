package fun.pullock.points.core.model.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(title = "积分历史")
@Data
public class HistoryVO {

    public HistoryVO() {
    }

    public HistoryVO(List<LogVO> logs) {
        this.logs = logs;
    }

    @Schema(title = "日志列表")
    private List<LogVO> logs;
}
