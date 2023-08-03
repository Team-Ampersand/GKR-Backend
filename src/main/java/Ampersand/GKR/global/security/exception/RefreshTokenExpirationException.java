package Ampersand.GKR.global.security.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class RefreshTokenExpirationException extends GkrException {

    public RefreshTokenExpirationException() {
        super(ErrorCode.TOKEN_IS_EXPIRED);
    }
}

