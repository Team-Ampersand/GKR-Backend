package Ampersand.GKR.domain.equipment.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class EquipmentNotFoundException extends GkrException {

    public EquipmentNotFoundException() {
        super(ErrorCode.EQUIPMENT_NOT_FOUND);
    }
}
