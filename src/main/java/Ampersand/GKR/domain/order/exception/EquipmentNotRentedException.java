package Ampersand.GKR.domain.order.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class EquipmentNotRentedException extends GkrException {

    public EquipmentNotRentedException() {
        super(ErrorCode.EQUIPMENT_NOT_RENTED);
    }
}
