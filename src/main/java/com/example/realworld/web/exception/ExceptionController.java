package com.example.realworld.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ErrorResponse exHandler(Exception e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse(e);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> bindExHandler(BindException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse(e));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> businessExHandler(BusinessException e) {
        return ResponseEntity.status(e.status())
                .body(new ErrorResponse(e));
    }

}
