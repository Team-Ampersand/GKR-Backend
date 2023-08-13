package Ampersand.GKR.domain.order.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderType {

    RENTAL, RETURN, EXTENSION;

    @JsonCreator
    public static OrderType from(String s) { return OrderType.valueOf(s.toUpperCase()); }
}
