package kr.co.belocal.web.exception;

public class AuthenticationNumberMismatchException extends NotFoundException {
    public AuthenticationNumberMismatchException(String value){
        super(value, ErrorCode.SMS_VERIFICATION_FAILED);
    }
}

