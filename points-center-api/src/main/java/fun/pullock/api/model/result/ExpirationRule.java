package fun.pullock.api.model.result;

import java.time.LocalDateTime;

public class ExpirationRule {

    /**
     * 类型，取值：1-相对过期时间（发放后多久过期）2-固定过期时间
     */
    private Integer type;

    private Integer days;

    private LocalDateTime expirationTime;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        return "ExpirationRule{" +
                "type=" + type +
                ", days=" + days +
                ", expirationTime=" + expirationTime +
                '}';
    }
}
