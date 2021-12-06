package com.kaixiang.security.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/6
 */
@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> resolveAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(Collections.singletonMap("message", "Access is denied"),
            HttpStatus.FORBIDDEN);
    }
}
