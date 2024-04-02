package fun.pullock.points.core.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogDetailDTO {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long userId;

    private Long configId;

    private String channelCode;

    private LocalDateTime expireTime;

    private Long total;

    private Long used;

    private Long currentUsed;

    private String source;

    private String uniqueSourceId;

    private Long bizId;

    private String bizDescription;
}
