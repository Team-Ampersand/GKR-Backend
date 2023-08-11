package Ampersand.GKR.domain.order.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RentalStatus {

    WAITING, ACCEPT, REJECT;

    @JsonCreator
    public static RentalStatus from(String s) { return RentalStatus.valueOf(s.toUpperCase()); }
}
