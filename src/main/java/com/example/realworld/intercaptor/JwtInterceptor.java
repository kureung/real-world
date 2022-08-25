package com.example.realworld.intercaptor;

import com.example.realworld.jwt.JwtTokenForLoggingIn;
import com.example.realworld.jwt.JwtTokenParser;
import com.example.realworld.jwt.config.JwtConfig;
import com.example.realworld.web.token.TokenParser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

    private static final String PUT = "PUT";

    private static final String GET = "GET";

    private static final String TOKEN = "token";

    private final TokenParser tokenParser;

    public JwtInterceptor(JwtConfig jwtConfig) {
        this(new JwtTokenParser(jwtConfig));
    }

    public JwtInterceptor(TokenParser tokenParser) {
        this.tokenParser = tokenParser;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        verifyRequest(request);
        return true;
    }

    private void verifyRequest(HttpServletRequest request) {
        if ("/api/user".equals(request.getContextPath())) {
            matchByHttpMethod(PUT, request);
            matchByHttpMethod(GET, request);
        }
    }

    private void matchByHttpMethod(String httpMethod, HttpServletRequest request) {
        if (httpMethod.equals(request.getMethod())) {
            JwtTokenForLoggingIn token = new JwtTokenForLoggingIn(request.getHeader(TOKEN));
            tokenParser.findEmailByToken(token);
            request.setAttribute(TOKEN, token);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
