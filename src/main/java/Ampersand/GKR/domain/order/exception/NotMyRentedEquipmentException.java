package Ampersand.GKR.domain.order.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class NotMyRentedEquipmentException extends GkrException {

    public NotMyRentedEquipmentException() {
        super(ErrorCode.NOT_MY_RENTED_EQUIPMENT);
    }
}
