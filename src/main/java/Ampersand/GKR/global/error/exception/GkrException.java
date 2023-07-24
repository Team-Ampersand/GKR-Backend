package Ampersand.GKR.global.error.exception;

import lombok.Getter;

@Getter
public class GkrException extends RuntimeException {

    private ErrorCode errorCode;

    public GkrException(ErrorCode errorCode) {

        super(errorCode.getMessage());

        this.errorCode = errorCode;
    }
}
