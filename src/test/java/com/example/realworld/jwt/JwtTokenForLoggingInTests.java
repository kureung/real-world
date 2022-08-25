package com.example.realworld.jwt;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.model.UserAccountInfo;
import com.example.realworld.jwt.config.JwtConfig;
import com.example.realworld.jwt.config.JwtKureungConfig;
import com.example.realworld.jwt.exception.NotValidTokenException;
import com.example.realworld.web.exception.ErrorCode;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class JwtTokenForLoggingInTests {

    private final JwtConfig jwtConfig = JwtKureungConfig.instance();

//    @Test
//    void 토큰_설정_정보와_유저와_발급시간을_토대로_토큰이_예외없이_생성된다() {
//        final LocalDateTime issuanceTime = LocalDateTime.of(2022, 8, 23, 12, 10, 10);
//        JwtToken sut = assertDoesNotThrow(() -> JwtToken.createByConfigAndUserAndIssuanceTime(new JwtKureungConfig(), user(), issuanceTime));
//        assertNotNull(sut.findEmailByJwtConfig(jwtConfig));
//    }

    @Test
    void 이미_로그인중인_유저의_토큰_정보를_토대로_토큰이_예외없이_생성된다() {
        String tokenValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        assertThatCode(() -> new JwtTokenForLoggingIn(tokenValue))
                .doesNotThrowAnyException();
    }

    @Test
    void 유효하지_않는_토큰_정보를_추출하면_예외가_발생한다() {
        String tokenValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWlhbCI6ImFiY0BuYXZlci5jb20ifQ.p-BB1uN_kY63JIN0vrN3VFA2yOkpF8ly7Rv4E_G0meg";
        JwtTokenForLoggingIn jwtTokenForLoggingIn = new JwtTokenForLoggingIn(tokenValue);
        assertThatThrownBy(() -> jwtTokenForLoggingIn.findEmailByJwtConfig(jwtConfig))
                .isInstanceOf(NotValidTokenException.class)
                .hasMessage(ErrorCode.NOT_VALID_TOKEN.message());
    }

    @Test
    void 토큰_정보를_잘_분석할_수있으면_예외가_발생하지_않는다() {
        String tokenValue = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWlhbCI6ImFiY0BuYXZlci5jb20ifQ.xrcTUw5Jb8akh_JV7yw0H0zOapelBOIEg1MOgINLEPu6XRejPdJLJP2Yn9OrRY1DEAmbUm8sFf4VY2E7DwIrqg";
        JwtTokenForLoggingIn jwtTokenForLoggingIn = assertDoesNotThrow(() -> new JwtTokenForLoggingIn(tokenValue));
        assertThatCode(() -> jwtTokenForLoggingIn.findEmailByJwtConfig(jwtConfig))
                .doesNotThrowAnyException();
    }

    private User user() {
        UserAccountInfo accountInfo = UserAccountInfo.builder()
                .username("name")
                .email("abc@naver.com")
                .password("password")
                .build();

        return User.builder()
                .userAccountInfo(accountInfo)
                .bio("I like an apple")
                .image("www.naver.com")
                .followingEmails(new HashSet<>())
                .build();
    }

}