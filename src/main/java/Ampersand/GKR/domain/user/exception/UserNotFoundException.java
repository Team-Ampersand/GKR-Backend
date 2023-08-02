package Ampersand.GKR.domain.user.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class UserNotFoundException extends GkrException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
