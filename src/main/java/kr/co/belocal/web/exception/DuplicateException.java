package kr.co.belocal.web.exception;



public class DuplicateException extends BusinessException {
    public DuplicateException(String value, ErrorCode errorCode) {
        super(value + "이(가) 중복입니다.", errorCode);
    }
}
