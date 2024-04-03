package fun.pullock.api.model.result;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PointsConfig implements Serializable {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String channelCode;

    private String name;

    private String description;

    private Integer status;

    private Integer type;

    private Long stock;

    private ExpirationRule expirationRule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public ExpirationRule getExpirationRule() {
        return expirationRule;
    }

    public void setExpirationRule(ExpirationRule expirationRule) {
        this.expirationRule = expirationRule;
    }

    @Override
    public String toString() {
        return "PointsConfig{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", channelCode='" + channelCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", stock=" + stock +
                ", expirationRule=" + expirationRule +
                '}';
    }
}
