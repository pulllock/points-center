package fun.pullock.points.core.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ConfigStatus {
    DISABLE(0, "禁用"),
    ENABLE(1, "启用"),
    ;

    private final int status;

    private final String desc;

    ConfigStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static ConfigStatus of(int status) {
        return Arrays.stream(values()).filter(t -> t.status == status).findFirst().orElse(null);
    }

}
