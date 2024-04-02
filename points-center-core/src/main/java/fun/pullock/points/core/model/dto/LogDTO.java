package fun.pullock.points.core.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LogDTO {

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

    private Integer status;

    private List<DetailDTO> detail;
}
