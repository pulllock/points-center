package fun.pullock.points.api.model.param;

import java.io.Serializable;

public class GrantParam implements Serializable {

    private Long userId;

    private Long configId;

    private String channelCode;

    private Long number;

    private String source;

    private String uniqueSourceId;

    private Long bizId;

    private String bizDescription;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUniqueSourceId() {
        return uniqueSourceId;
    }

    public void setUniqueSourceId(String uniqueSourceId) {
        this.uniqueSourceId = uniqueSourceId;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public String getBizDescription() {
        return bizDescription;
    }

    public void setBizDescription(String bizDescription) {
        this.bizDescription = bizDescription;
    }

    @Override
    public String toString() {
        return "GrantParam{" +
                "userId=" + userId +
                ", configId=" + configId +
                ", channelCode='" + channelCode + '\'' +
                ", number=" + number +
                ", source='" + source + '\'' +
                ", uniqueSourceId='" + uniqueSourceId + '\'' +
                ", bizId=" + bizId +
                ", bizDescription='" + bizDescription + '\'' +
                '}';
    }
}
