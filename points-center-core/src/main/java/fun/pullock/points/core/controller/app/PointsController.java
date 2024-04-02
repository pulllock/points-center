package fun.pullock.points.core.controller.app;

import fun.pullock.points.core.model.app.vo.DetailVO;
import fun.pullock.points.core.model.app.vo.HistoryVO;
import fun.pullock.points.core.model.app.vo.SummaryVO;
import fun.pullock.points.core.service.PointsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "积分APP端接口")
@RestController
@RequestMapping("/app/points")
public class PointsController {

    @Resource
    private PointsService pointsService;

    @Operation(summary = "用户积分汇总")
    @GetMapping("/summary")
    public SummaryVO summary() {
        Long userId = 1L;
        return pointsService.summary(userId);
    }

    @Operation(summary = "用户积分详情")
    @GetMapping("/detail")
    public DetailVO detail() {
        Long userId = 1L;
        return pointsService.detail(userId);
    }

    @Operation(summary = "用户积分历史")
    @GetMapping("/history")
    public HistoryVO history() {
        Long userId = 1L;
        return pointsService.history(userId);
    }
}
