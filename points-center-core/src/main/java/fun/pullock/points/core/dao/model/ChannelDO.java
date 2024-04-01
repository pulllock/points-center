package fun.pullock.points.core.dao.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChannelDO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String code;

    private String name;
}