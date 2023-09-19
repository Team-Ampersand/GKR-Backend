package Ampersand.GKR.domain.equipment.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EquipmentType {

    MONITOR, PHONE, LAPTOP, KEYBOARD, PIE, CONSUMABLE, ETC;

    @JsonCreator
    public static EquipmentType from(String s) { return EquipmentType.valueOf(s.toUpperCase()); }
}
