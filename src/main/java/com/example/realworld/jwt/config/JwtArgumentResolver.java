package com.example.realworld.jwt.config;

import com.example.realworld.jwt.JwtTokenForLoggingIn;
import com.example.realworld.web.token.Token;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class JwtArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String TOKEN = "token";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasJwtAnnotation = parameter.hasParameterAnnotation(Jwt.class);
        boolean hasTokenType = Token.class.isAssignableFrom(parameter.getParameterType());
        return hasJwtAnnotation && hasTokenType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String tokenInfo = request.getHeader(TOKEN);
        JwtTokenForLoggingIn token = new JwtTokenForLoggingIn(tokenInfo);
        verifyTokenInfo(tokenInfo);
        return new JwtTokenForLoggingIn(tokenInfo);
    }

    private void verifyTokenInfo(String tokenInfo) {
        if (Objects.isNull(tokenInfo) || tokenInfo.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

}
