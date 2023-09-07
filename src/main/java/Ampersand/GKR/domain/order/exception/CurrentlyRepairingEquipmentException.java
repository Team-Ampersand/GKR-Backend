package Ampersand.GKR.domain.order.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class CurrentlyRepairingEquipmentException extends GkrException {

    public CurrentlyRepairingEquipmentException() {
        super(ErrorCode.CURRENTLY_REPAIRING_EQUIPMENT);
    }
}
