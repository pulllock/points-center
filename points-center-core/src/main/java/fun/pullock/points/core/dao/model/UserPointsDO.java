package fun.pullock.points.core.dao.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPointsDO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long userId;

    private Long total;

    private Long used;

    private Long expired;
}