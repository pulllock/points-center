package fun.pullock.api.model.param;

import java.io.Serializable;

public class RollbackParam implements Serializable {

    private Long userId;

    private String channelCode;

    private String source;

    private String uniqSourceId;

    private String originUniqSourceId;

    private Long bizId;

    private String bizDescription;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
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

    public String getOriginUniqSourceId() {
        return originUniqSourceId;
    }

    public void setOriginUniqSourceId(String originUniqSourceId) {
        this.originUniqSourceId = originUniqSourceId;
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
        return "RollbackParam{" +
                "userId=" + userId +
                ", channelCode='" + channelCode + '\'' +
                ", source='" + source + '\'' +
                ", uniqSourceId='" + uniqSourceId + '\'' +
                ", originUniqSourceId='" + originUniqSourceId + '\'' +
                ", bizId=" + bizId +
                ", bizDescription='" + bizDescription + '\'' +
                '}';
    }
}
