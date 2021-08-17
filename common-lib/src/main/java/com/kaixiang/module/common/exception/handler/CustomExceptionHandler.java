package com.kaixiang.module.common.exception.handler;

import com.kaixiang.module.common.exception.RecordNotFoundException;
import com.kaixiang.module.common.exception.UnAuthorizedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> resolveRecordNotFoundException(RecordNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<?> resolveUnAuthorizedException(UnAuthorizedException ex) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
