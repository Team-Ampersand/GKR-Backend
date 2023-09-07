package Ampersand.GKR.domain.order.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class EquipmentAlreadyWaitingException extends GkrException {

    public EquipmentAlreadyWaitingException() {
        super(ErrorCode.ALREADY_WAITING_EQUIPMENT);
    }
}
