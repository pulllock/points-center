package fun.pullock.points.core.dao.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PointsConfigDO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String channelCode;

    private String name;

    private String description;

    private Integer status;

    private Integer type;

    private Long stock;

    private String expirationRule;
}