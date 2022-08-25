//package com.example.realworld.intercaptor;
//
//import com.example.realworld.domain.user.model.User;
//import com.example.realworld.domain.user.model.UserAccountInfo;
//import com.example.realworld.jwt.JwtToken;
//import com.example.realworld.jwt.JwtTokenParser;
//import com.example.realworld.jwt.config.JwtConfig;
//import com.example.realworld.jwt.config.JwtKureungConfig;
//import com.example.realworld.web.token.Token;
//import com.example.realworld.web.token.TokenParser;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//
//class JwtInterceptorTests {
//
//    private final MockHttpServletRequest request = new MockHttpServletRequest();
//    private final MockHttpServletResponse response = new MockHttpServletResponse();
//
//    @BeforeEach
//    void beforeEach() {
//        request.clearAttributes();
//    }
//
//    @Test
//    void jwt_토큰이_있으면_핸들러가_true_값을_반환한다() throws Exception {
//        TokenParser tokenParser = tokenParser();
////        HandlerInterceptor sut = new JwtInterceptor();
//
////        request.setAttribute("token", toekn());
////        assertTrue(sut.preHandle(request, response, ""));
//    }
//
//    @Test
//    void jwt_토큰이_없으면_핸들러가_false_값을_반환한다() throws Exception {
////        TokenParser tokenParser = tokenParser();
////        HandlerInterceptor sut = new JwtInterceptor();
////
////        assertFalse(sut.preHandle(request, response, ""));
//    }
//
//    private TokenParser tokenParser() {
//        JwtConfig jwtConfig = new JwtKureungConfig();
//        return new JwtTokenParser();
//    }
//
//    private Token toekn() {
//        JwtConfig jwtConfig = new JwtKureungConfig();
//        return new JwtToken("");
//    }
//
//    private User user() {
//        UserAccountInfo accountInfo = UserAccountInfo.builder()
//                .username("name")
//                .email("abc@naver.com")
//                .password("password")
//                .build();
//
//        return User.builder()
//                .userAccountInfo(accountInfo)
//                .build();
//    }
//
//}