package Ampersand.GKR.domain.violation.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class CurrentlyViolatingException extends GkrException {

    public CurrentlyViolatingException() {
        super(ErrorCode.CURRENTLY_VIOLATING);
    }
}
