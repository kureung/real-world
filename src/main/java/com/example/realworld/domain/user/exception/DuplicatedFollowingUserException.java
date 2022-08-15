package com.example.realworld.domain.user.exception;

import com.example.realworld.web.exception.BusinessException;
import com.example.realworld.web.exception.ErrorCode;

public class DuplicatedFollowingUserException extends BusinessException {

    public DuplicatedFollowingUserException(ErrorCode errorCode) {
        super(errorCode);
    }

}
