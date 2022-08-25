//package com.example.realworld.jwt.config;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import com.example.realworld.web.token.Token;
//import java.lang.reflect.Method;
//import java.util.stream.Stream;
//import javax.validation.Valid;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.core.MethodParameter;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//
//class JwtArgumentResolverTests {
//
//    private final HandlerMethodArgumentResolver sut = new JwtArgumentResolver();
//
//    @Test
//    @DisplayName("supportsParameter는 @Jwt 애노테이션과 Token 타입이어야 true가 반환된다.")
//    void supportsParameter_성공_테스트() throws NoSuchMethodException {
//        Method method = this.getClass().getDeclaredMethod("supportsParameter_성공_테스트_예제1", Token.class);
//        MethodParameter parameter = new MethodParameter(method, 0);
//        assertTrue(assertDoesNotThrow(() -> sut.supportsParameter(parameter)));
//    }
//
//    private void supportsParameter_성공_테스트_예제1(@Jwt Token str) {
//    }
//
//    @MethodSource
//    @ParameterizedTest
//    @DisplayName("supportsParameter는 @Jwt 애노테이션과 Token 타입이어야 true가 반환된다.")
//    void supportsParameter_실패_테스트(Method method) throws NoSuchMethodException {
//        MethodParameter parameter = new MethodParameter(method, 0);
//        assertFalse(assertDoesNotThrow(() -> sut.supportsParameter(parameter)));
//    }
//
//    static Stream<Arguments> supportsParameter_실패_테스트() throws NoSuchMethodException {
//        return Stream.of(
//                Arguments.of(JwtArgumentResolverTests.class.getDeclaredMethod("supportsParameter_실패_테스트_예제1", String.class)),
//                Arguments.of(JwtArgumentResolverTests.class.getDeclaredMethod("supportsParameter_실패_테스트_예제2", Token.class)),
//                Arguments.of(JwtArgumentResolverTests.class.getDeclaredMethod("supportsParameter_실패_테스트_예제3", String.class))
//        );
//    }
//
//    private void supportsParameter_실패_테스트_예제1(String str) {
//    }
//
//    private void supportsParameter_실패_테스트_예제2(@Valid Token str) {
//    }
//
//    private void supportsParameter_실패_테스트_예제3(@Jwt String str) {
//    }
//
//    @Test
//    void resolveArgument_테스트() throws NoSuchMethodException {
//        Method method = this.getClass().getDeclaredMethod("supportsParameter_성공_테스트_예제1", Token.class);
//        MethodParameter parameter = new MethodParameter(method, 0);
//        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
//        ServletWebRequest servletWebRequest = new ServletWebRequest(mockHttpServletRequest);
//
//        Object result = assertDoesNotThrow(() -> sut.resolveArgument(parameter, null, servletWebRequest, null));
//        System.out.println("result = " + result);
//
//        assertThat(result).isInstanceOf(Token.class);
//    }
//
//}