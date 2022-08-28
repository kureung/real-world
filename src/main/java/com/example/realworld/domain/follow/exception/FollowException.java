package com.example.realworld.domain.follow.exception;

import com.example.realworld.web.exception.BusinessException;
import com.example.realworld.web.exception.ErrorCode;

public class FollowException extends BusinessException {

    public FollowException(ErrorCode errorCode) {
        super(errorCode);
    }

}
