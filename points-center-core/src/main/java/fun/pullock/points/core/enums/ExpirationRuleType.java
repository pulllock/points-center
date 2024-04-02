package fun.pullock.points.core.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ExpirationRuleType {
    RELATIVE(1, "相对"),
    FIX(2, "固定"),
    ;

    private final int type;

    private final String desc;

    ExpirationRuleType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static ExpirationRuleType of(int type) {
        return Arrays.stream(values()).filter(t -> t.type == type).findFirst().orElse(null);
    }

}
