package fun.pullock.api.model.param;

import java.io.Serializable;

public class GrantParam implements Serializable {

    private Long userId;

    private Long configId;

    private String channelCode;

    private Long number;

    private String source;

    private String uniqSourceId;

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

    public String getUniqSourceId() {
        return uniqSourceId;
    }

    public void setUniqSourceId(String uniqSourceId) {
        this.uniqSourceId = uniqSourceId;
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
                ", uniqSourceId='" + uniqSourceId + '\'' +
                ", bizId=" + bizId +
                ", bizDescription='" + bizDescription + '\'' +
                '}';
    }
}
