package Ampersand.GKR.domain.file.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class InvalidFormatFileException extends GkrException {

    public InvalidFormatFileException() {
        super(ErrorCode.INVALID_FORMAT_FILE);
    }
}

