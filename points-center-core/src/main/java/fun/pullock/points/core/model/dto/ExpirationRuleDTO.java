package fun.pullock.points.core.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpirationRuleDTO {

    /**
     * 类型，取值：1-相对过期时间（发放后多久过期）2-固定过期时间
     */
    private Integer type;

    private Integer days;

    private LocalDateTime expirationTime;
}
