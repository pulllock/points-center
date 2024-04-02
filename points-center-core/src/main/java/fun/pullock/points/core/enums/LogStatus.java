package fun.pullock.points.core.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum LogStatus {
    PROCESSING(1, "处理中"),
    FAILED(2, "失败"),
    SUCCESS(3, "成功")
    ;

    private final int status;

    private final String desc;

    LogStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static LogStatus of(int status) {
        return Arrays.stream(values()).filter(t -> t.status == status).findFirst().orElse(null);
    }

}
