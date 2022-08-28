package com.example.realworld.web.exception;

import org.springframework.http.HttpStatus;

/**
 * 승인되지 않은 요청의 경우 401 : HttpStatus.UNAUTHORIZED (요청에 인증이 필요하지만 제공되지 않는 경우)
 * <p>
 * 금지된 요청의 경우 403 : HttpStatus.FORBIDDEN (요청이 유효할 수 있지만 사용자에게 작업을 수행할 권한이 없는 경우)
 * <p>
 * 찾을 수 없는 요청의 경우 404 : HttpStatus.NOT_FOUND (요청을 수행할 리소스를 찾을 수 없는 경우)
 * <p>
 * 요청이 유효성 검사에 실패하면 422 : UNPROCESSABLE_ENTITY
 */
public enum ErrorCode {

    NO_SUCH_USER_ELEMENT("회원을 조회할 수 없습니다.", HttpStatus.NOT_FOUND),

    NOT_VALID_TOKEN("유효한 토큰이 아닙니다.", HttpStatus.FORBIDDEN),

    DUPLICATED_EMAIL("이미 존재하는 이메일입니다.", HttpStatus.UNPROCESSABLE_ENTITY),

    NOT_VALID_LOGIN("아이디 또는 비밀번호가 틀렸습니다.", HttpStatus.NOT_FOUND),

    DUPLICATED_FOLLOWING_USER("이미 팔로우한 유저입니다.", HttpStatus.UNPROCESSABLE_ENTITY),

    NOT_FOUND_FOLLOWING_USER("팔로잉을 찾을 수 없습니다.", HttpStatus.UNPROCESSABLE_ENTITY),
    ;

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String message() {
        return message;
    }

    public HttpStatus status() {
        return status;
    }
}
