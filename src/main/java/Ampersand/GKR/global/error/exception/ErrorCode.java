package Ampersand.GKR.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //SERVER ERROR
    UNKNOWN_ERROR("알 수 없는 에러입니다.", 500),

    //TOKEN
    TOKEN_IS_EXPIRED("토큰이 만료 되었습니다.", 401),
    TOKEN_NOT_VALID("토큰이 유효 하지 않습니다.", 401),

    //USER
    ROLE_NOT_EXIST("역할이 존재하지 않습니다.", 404),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다", 404);

    private final String message;

    private final int status;
}
