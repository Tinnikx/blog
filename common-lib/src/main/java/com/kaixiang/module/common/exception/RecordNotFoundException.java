package com.kaixiang.module.common.exception;

public class RecordNotFoundException extends Exception{

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
