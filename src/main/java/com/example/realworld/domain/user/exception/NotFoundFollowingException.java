package com.example.realworld.domain.user.exception;

import com.example.realworld.web.exception.BusinessException;
import com.example.realworld.web.exception.ErrorCode;

public class NotFoundFollowingException extends BusinessException {

    public NotFoundFollowingException(ErrorCode errorCode) {
        super(errorCode);
    }

}
