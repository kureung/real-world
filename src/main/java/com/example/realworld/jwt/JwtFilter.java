package com.example.realworld.jwt;

import static java.util.Objects.isNull;

import com.example.realworld.jwt.exception.NotValidTokenException;
import com.example.realworld.web.exception.ErrorCode;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String PUT = "PUT";

    private static final String GET = "GET";

    private static final String TOKEN = "token";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("/api/user".equals(request.getContextPath())) {
            matchByHttpMethod(PUT, request);
            matchByHttpMethod(GET, request);
        }
        filterChain.doFilter(request, response);
    }

    private void matchByHttpMethod(String httpMethod, HttpServletRequest request) {
        if (httpMethod.equals(request.getMethod())) {
            verifyInvalidToken(request.getHeader(TOKEN));
        }
    }

    private void verifyInvalidToken(String token) {
        if (isNull(token)) {
            throw new NotValidTokenException(ErrorCode.NOT_VALID_TOKEN);
        }
    }

}
