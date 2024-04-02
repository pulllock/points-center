package fun.pullock.points.core.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChannelDTO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String code;

    private String name;
}
