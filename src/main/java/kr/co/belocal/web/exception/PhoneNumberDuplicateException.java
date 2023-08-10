package kr.co.belocal.web.exception;


import  kr.co.belocal.web.exception.ErrorCode;
import  kr.co.belocal.web.exception.DuplicateException;

public class PhoneNumberDuplicateException extends DuplicateException {

    public PhoneNumberDuplicateException(String value){
        super(value, ErrorCode.PHONE_NUMBER_DUPLICATE);
    }
}
