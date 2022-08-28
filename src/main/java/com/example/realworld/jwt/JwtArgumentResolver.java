package com.example.realworld.jwt;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class JwtArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasJwtAnnotation = parameter.hasParameterAnnotation(Jwt.class);
        boolean hasTokenType = Token.class.isAssignableFrom(parameter.getParameterType());

        return hasJwtAnnotation && hasTokenType && isAnnotationRequired(parameter);
    }

    private static boolean isAnnotationRequired(MethodParameter parameter) {
        Jwt jwtAnnotation = parameter.getParameterAnnotation(Jwt.class);
        return jwtAnnotation.required();
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        request.getHeader("Token");


        return null;
    }

}
