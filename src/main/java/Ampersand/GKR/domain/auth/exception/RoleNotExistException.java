package Ampersand.GKR.domain.auth.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class RoleNotExistException extends GkrException {

    public RoleNotExistException() {
        super(ErrorCode.ROLE_NOT_EXIST);
    }
}
