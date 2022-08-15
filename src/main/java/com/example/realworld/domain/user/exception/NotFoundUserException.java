package com.example.realworld.domain.user.exception;

import com.example.realworld.web.exception.BusinessException;
import com.example.realworld.web.exception.ErrorCode;

public class NotFoundUserException extends BusinessException {

    public NotFoundUserException(ErrorCode errorCode) {
        super(errorCode);
    }

}
