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
    USER_NOT_FOUND("사용자를 찾을 수 없습니다", 404),

    //FILE
    FILE_UPLOAD_FAIL("파일 업로드에 실패했습니다.", 500),
    INVALID_FORMAT_FILE("잘못된 형식의 파일입니다.", 400),

    //EQUIPMENT
    EQUIPMENT_NOT_FOUND("기자재를 찾을 수 없습니다.", 404),

    //ORDER
    ALREADY_ORDER_EQUIPMENT("이미 대여된 기자재 입니다.", 409),
    ALREADY_RETURN_STATE("이미 반납 신청 상태입니다.", 409),
    APPLICATION_NOT_FOUND("신청 하신 사항을 찾을 수 없습니다.", 404),
    NOT_MY_RENTED_EQUIPMENT("내가 대여한 기자재가 아닙니다.", 403),
    EQUIPMENT_NOT_RENTED("기자재가 대여되지 않았습니다.", 400),

    //VIOLATION
    CURRENTLY_VIOLATING("현재 제재를 당한 상태입니다.", 403);

    private final String message;

    private final int status;
}
