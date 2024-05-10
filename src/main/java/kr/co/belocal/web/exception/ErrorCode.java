package kr.co.belocal.web.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    METHOD_NOT_ALLOWED(405, "C001", "Method Not Allowed"),
    INVALID_INPUT_VALUE(400, "C002", "입력값이 잘못되었습니다."),
    INVALID_TOKEN(401, "C003", "권한이 없습니다."),
    INSUFFICIENT_SCOPE(403, "C005", "권한이 부족합니다."),
    NOT_FOUND(404, "C006", "찾을 수 없습니다."),
    INCORRECT_FORMAT(400, "C008", "잘못된 형식입니다."),
    INTERNAL_SERVER_ERROR(500, "C050", "Server Error"),


    //member
    MEMBER_NOT_FOUND(404, "M001", "사용자를 찾을 수 없습니다."),
    PHONE_NUMBER_DUPLICATE(409, "M005", "휴대폰 번호가 중복됩니다."),
    SMS_VERIFICATION_FAILED(400, "M003", "SMS 인증 실패"),
    NOT_ACTIVATED(404, "M010", "비활성화 된 사용자입니다."),

    // FILE
    FILE_UPLOAD_SIZE_EXCEEDED(413, "F001", "최대 업로드 크기를 초과합니다."),
    FILE_NOT_EXISTED(400, "F004", "파일이 존재하지 않습니다."),
    FILE_IO_FAILED(500, "F003", "파일 입출력 실패."),


    // Naver
    SMS_API_CALL_FAILED(500, "N001", "SMS API 요청에 실패하였습니다.");


    private final int status;

    private final String code;

    private final String message;

}
