package Ampersand.GKR.domain.violation.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class AlreadyViolatingException extends GkrException {

    public AlreadyViolatingException() {
        super(ErrorCode.ALREADY_VIOLATING);
    }
}
