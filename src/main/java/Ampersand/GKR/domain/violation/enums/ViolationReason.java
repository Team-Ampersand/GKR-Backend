package Ampersand.GKR.domain.violation.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ViolationReason {

    OVERDUE, DAMAGE, ETC;

    @JsonCreator
    public static ViolationReason from(String s) {
        return ViolationReason.valueOf(s.toUpperCase());
    }
}
