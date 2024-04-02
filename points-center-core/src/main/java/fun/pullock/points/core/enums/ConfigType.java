package fun.pullock.points.core.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ConfigType {
    UNLIMITED(1, "永久积分"),
    ACTIVITY(2, "活动积分"),
    ;

    private final int type;

    private final String desc;

    ConfigType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static ConfigType of(int type) {
        return Arrays.stream(values()).filter(t -> t.type == type).findFirst().orElse(null);
    }

}
