package com.example.realworld.jwt.exception;

import com.example.realworld.web.exception.BusinessException;
import com.example.realworld.web.exception.ErrorCode;

public class NotValidTokenException extends BusinessException {

    public NotValidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }

}
