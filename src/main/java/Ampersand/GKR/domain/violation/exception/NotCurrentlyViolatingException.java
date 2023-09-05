package Ampersand.GKR.domain.violation.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class NotCurrentlyViolatingException extends GkrException {

    public NotCurrentlyViolatingException() {
        super(ErrorCode.NOT_CURRENTLY_VIOLATING);
    }
}
