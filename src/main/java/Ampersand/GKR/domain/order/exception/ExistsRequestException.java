package Ampersand.GKR.domain.order.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class ExistsRequestException extends GkrException {

    public ExistsRequestException() {
        super(ErrorCode.REQUEST_IS_EXISTS);
    }
}
