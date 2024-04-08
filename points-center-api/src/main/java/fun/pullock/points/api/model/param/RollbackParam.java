package fun.pullock.points.api.model.param;

import java.io.Serializable;

public class RollbackParam implements Serializable {

    private Long userId;

    private String channelCode;

    private String source;

    private String uniqueSourceId;

    private String originUniqueSourceId;

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

    public String getUniqueSourceId() {
        return uniqueSourceId;
    }

    public void setUniqueSourceId(String uniqueSourceId) {
        this.uniqueSourceId = uniqueSourceId;
    }

    public String getOriginUniqueSourceId() {
        return originUniqueSourceId;
    }

    public void setOriginUniqueSourceId(String originUniqueSourceId) {
        this.originUniqueSourceId = originUniqueSourceId;
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
                ", uniqueSourceId='" + uniqueSourceId + '\'' +
                ", originUniqueSourceId='" + originUniqueSourceId + '\'' +
                ", bizId=" + bizId +
                ", bizDescription='" + bizDescription + '\'' +
                '}';
    }
}
