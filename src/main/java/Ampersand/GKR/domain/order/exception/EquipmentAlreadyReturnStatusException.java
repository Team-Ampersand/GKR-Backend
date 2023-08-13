package Ampersand.GKR.domain.order.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class EquipmentAlreadyReturnStatusException extends GkrException {

    public EquipmentAlreadyReturnStatusException() {
        super(ErrorCode.ALREADY_RETURN_STATE);
    }
}
