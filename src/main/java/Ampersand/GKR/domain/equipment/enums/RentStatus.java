package Ampersand.GKR.domain.equipment.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RentStatus {

    NOT_RENT, RENTING;

    @JsonCreator
    public static RentStatus from(String s) { return RentStatus.valueOf(s.toUpperCase()); }
}
