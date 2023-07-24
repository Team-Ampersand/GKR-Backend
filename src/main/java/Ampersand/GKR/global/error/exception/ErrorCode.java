package Ampersand.GKR.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {;

    private final String message;

    private final int status;
}
