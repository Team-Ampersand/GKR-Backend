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
    ALREADY_RENTING_EQUIPMENT("이미 대여된 기자재 입니다.", 409),
    CURRENTLY_REPAIRING_EQUIPMENT("현재 수리중인 기자재 입니다.", 409),
    ALREADY_WAITING_EQUIPMENT("이미 대여 신청중인 기자재 입니다.", 409),
    ALREADY_RETURN_STATE("이미 반납 신청중인 기자재 입니다.", 409),
    APPLICATION_NOT_FOUND("신청 하신 사항을 찾을 수 없습니다.", 404),
    NOT_MY_RENTED_EQUIPMENT("내가 대여한 기자재가 아닙니다.", 403),
    REQUEST_IS_EXISTS("대기중인 요청이나 현재 기자재의 사용자가 존재합니다.", 409),

    //VIOLATION
    CURRENTLY_VIOLATING("현재 제재를 당한 상태입니다.", 403),
    NOT_CURRENTLY_VIOLATING("현재 제재를 당한 상태가 아닙니다.", 400),
    ALREADY_VIOLATING("이미 제재중인 상태입니다.", 409),

    //NOTICE
    NOTICE_NOT_FOUND("공지글을 찾을 수 없습니다.", 404);

    private final String message;

    private final int status;
}
