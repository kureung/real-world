package com.example.realworld.domain.user.exception;

import com.example.realworld.web.exception.BusinessException;
import com.example.realworld.web.exception.ErrorCode;

public class DuplicatedEmailException extends BusinessException {

    public DuplicatedEmailException(ErrorCode errorCode) {
        super(errorCode);
    }

}
