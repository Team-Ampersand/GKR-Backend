package Ampersand.GKR.domain.violation.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class ViolationNotFoundException extends GkrException {

    public ViolationNotFoundException() {
        super(ErrorCode.VIOLATION_NOT_FOUND);
    }
}