package com.theralink.common.exception;


public class UnAuthorizeException extends ApplicationException {


    public UnAuthorizeException(String exceptionKey, int errorCode) {
        super(exceptionKey, errorCode);
    }
}
