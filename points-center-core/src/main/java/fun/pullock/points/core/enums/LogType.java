package fun.pullock.points.core.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum LogType {
    GRANT(1, "发放"),
    USE(2, "使用"),
    ROLLBACK(3, "回退"),
    RECLAIM(4, "回收"),
    ;

    private final int type;

    private final String desc;

    LogType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static LogType of(int type) {
        return Arrays.stream(values()).filter(t -> t.type == type).findFirst().orElse(null);
    }

}
