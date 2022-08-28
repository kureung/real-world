package com.example.realworld.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtArgumentResolverTest {

    private final HandlerMethodArgumentResolver sut = new JwtArgumentResolver();

    @MethodSource
    @ParameterizedTest
    @DisplayName("supportsParameter는 파라미터가 [@Jwt 어노테이션] 또는 [Token 타입] 또는 [@Jwt 어노테이션의 required 속성이 true] 가 아닐경우 거짓이 반환된다.")
    void supportsParameter_실패_테스트(Method method) {
        MethodParameter parameter = new MethodParameter(method, 0);
        assertFalse(sut.supportsParameter(parameter));
    }

    private static Stream<Arguments> supportsParameter_실패_테스트() throws NoSuchMethodException {
        return Stream.of(
                Arguments.of(JwtArgumentResolverTest.class.getDeclaredMethod("supportsParameter_실패_테스트_예제1", String.class)),
                Arguments.of(JwtArgumentResolverTest.class.getDeclaredMethod("supportsParameter_실패_테스트_예제2", Token.class)),
                Arguments.of(JwtArgumentResolverTest.class.getDeclaredMethod("supportsParameter_실패_테스트_예제3", String.class)),
                Arguments.of(JwtArgumentResolverTest.class.getDeclaredMethod("supportsParameter_실패_테스트_예제4", Token.class))
        );
    }

    private void supportsParameter_실패_테스트_예제1(String str) {
    }

    private void supportsParameter_실패_테스트_예제2(@Valid Token str) {
    }

    private void supportsParameter_실패_테스트_예제3(@Jwt String str) {
    }

    private void supportsParameter_실패_테스트_예제4(@Jwt(required = false) Token str) {
    }

    @Test
    @DisplayName("supportsParameter는 파라미터가 [@Jwt 어노테이션], [Token 타입], [@Jwt 어노테이션의 required 속성이 true] 일 경우 참이 반환된다.")
    void supportsParameter_성공_테스트() throws NoSuchMethodException {
        Method method = this.getClass().getDeclaredMethod("supportsParameter_성공_테스트_예제1", Token.class);
        MethodParameter parameter = new MethodParameter(method, 0);
        assertTrue(sut.supportsParameter(parameter));
    }

    private void supportsParameter_성공_테스트_예제1(@Jwt Token str) {
    }
    
}