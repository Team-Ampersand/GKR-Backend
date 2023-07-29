package Ampersand.GKR.global.security.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class TokenExpirationException extends GkrException {

    public TokenExpirationException() {
        super(ErrorCode.TOKEN_IS_EXPIRED);
    }
}
