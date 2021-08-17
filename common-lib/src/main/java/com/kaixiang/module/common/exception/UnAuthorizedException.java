package com.kaixiang.module.common.exception;

public class UnAuthorizedException extends Exception {

    public UnAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizedException(String message) {
        super(message);
    }
}
