package Ampersand.GKR.global.security.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class TokenNotValidException extends GkrException {

    public TokenNotValidException() {
        super(ErrorCode.TOKEN_NOT_VALID);
    }
}