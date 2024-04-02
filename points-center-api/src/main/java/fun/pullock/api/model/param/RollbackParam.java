package fun.pullock.api.model.param;

import java.io.Serializable;

public class RollbackParam implements Serializable {

    private Long userId;

    private String channelCode;

    private String source;

    private String uniqueSourceId;

    private String originuniqueSourceId;

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

    public String getuniqueSourceId() {
        return uniqueSourceId;
    }

    public void setuniqueSourceId(String uniqueSourceId) {
        this.uniqueSourceId = uniqueSourceId;
    }

    public String getOriginuniqueSourceId() {
        return originuniqueSourceId;
    }

    public void setOriginuniqueSourceId(String originuniqueSourceId) {
        this.originuniqueSourceId = originuniqueSourceId;
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
                ", originuniqueSourceId='" + originuniqueSourceId + '\'' +
                ", bizId=" + bizId +
                ", bizDescription='" + bizDescription + '\'' +
                '}';
    }
}
