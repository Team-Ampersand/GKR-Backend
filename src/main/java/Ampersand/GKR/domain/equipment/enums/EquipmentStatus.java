package Ampersand.GKR.domain.equipment.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EquipmentStatus {

    NOT_RENT, WAITING, RENTING;

    @JsonCreator
    public static EquipmentStatus from(String s) { return EquipmentStatus.valueOf(s.toUpperCase()); }
}
