package Ampersand.GKR.domain.order.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class EquipmentAlreadyRentalException extends GkrException {

    public EquipmentAlreadyRentalException() {
        super(ErrorCode.ALREADY_RENTING_EQUIPMENT);
    }
}
