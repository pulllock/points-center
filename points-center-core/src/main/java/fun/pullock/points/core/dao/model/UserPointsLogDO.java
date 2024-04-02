package fun.pullock.points.core.dao.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPointsLogDO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long userId;

    private Long configId;

    private String channelCode;

    private Integer type;

    private Long number;

    private String source;

    private String uniqueSourceId;

    private Long bizId;

    private String bizDescription;

    private String detail;
}