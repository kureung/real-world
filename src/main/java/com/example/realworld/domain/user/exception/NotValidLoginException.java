package com.example.realworld.domain.user.exception;

import com.example.realworld.web.exception.BusinessException;
import com.example.realworld.web.exception.ErrorCode;

public class NotValidLoginException extends BusinessException {

    public NotValidLoginException(ErrorCode errorCode) {
        super(errorCode);
    }

}
