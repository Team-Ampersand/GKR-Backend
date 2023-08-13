package Ampersand.GKR.domain.order.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderStatus {

    WAITING, ACCEPT, REJECT;

    @JsonCreator
    public static OrderStatus from(String s) { return OrderStatus.valueOf(s.toUpperCase()); }
}
