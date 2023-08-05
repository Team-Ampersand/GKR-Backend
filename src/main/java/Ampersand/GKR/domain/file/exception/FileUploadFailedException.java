package Ampersand.GKR.domain.file.exception;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.GkrException;
import lombok.Getter;

@Getter
public class FileUploadFailedException extends GkrException {

    public FileUploadFailedException() {
        super(ErrorCode.FILE_UPLOAD_FAIL);
    }
}
