package Ampersand.GKR.domain.order.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RentalType {

    RENTAL, RETURN, EXTENSION;

    @JsonCreator
    public static RentalType from(String s) { return RentalType.valueOf(s.toUpperCase()); }
}
