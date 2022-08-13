package com.example.realworld.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.realworld.domain.user.UserRepository.UserFakeRepository;
import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.model.UserAccountInfo;
import com.example.realworld.domain.user.passwordencoder.PasswordEncryption.FakePasswordEncryption;
import com.example.realworld.domain.user.service.UserService;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserServiceTests {

    private final UserRepository userRepository = UserFakeRepository.instance();
    private final UserUseCase sut = new UserService(userRepository, FakePasswordEncryption.instance());

    @BeforeEach
    void beforeEach() {
        UserFakeRepository instance = (UserFakeRepository) userRepository;
        instance.clear();
    }

    @Test
    @DisplayName("로그인 테스트")
    void authenticationTest() {
        // given
        User savedUser = savedUser();

        UserAccountInfo loginAccountInfo = UserAccountInfo.builder()
                .email(savedUser.email())
                .password(savedUser.password())
                .build();
        User loginUser = User.builder()
                .userAccountInfo(loginAccountInfo)
                .build();

        // when
        String loginUserEmail = assertDoesNotThrow(() -> sut.login(loginUser));

        // then
        assertNotNull(loginUserEmail);
    }

    @Test
    @DisplayName("로그인 예외 테스트")
    void authenticationExceptionTest() {
        // given
        User savedUser = savedUser();

        UserAccountInfo accountInfo = UserAccountInfo.builder()
                .email(savedUser.email())
                .password(savedUser.password() + "1")
                .build();
        User loginUser = User.builder()
                .userAccountInfo(accountInfo)
                .build();

        // when, then
        assertThatThrownBy(() -> sut.login(loginUser))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("비밀번호가 틀립니다.");
    }

    @Test
    @DisplayName("회원가입 테스트")
    void registrationTest() {
        UserAccountInfo accountInfo = UserAccountInfo.builder()
                .username("Jacob")
                .email("jake@jake.jake")
                .password("jakejake")
                .build();

        User user = User.builder()
                .userAccountInfo(accountInfo)
                .build();

        User savedUser = assertDoesNotThrow(() -> sut.save(user));

        assertNotNull(savedUser);
    }

    @Test
    @DisplayName("조회 테스트")
    void getCurrentUserTest() {
        // given
        User savedUser = savedUser();

        // when
        User findUser = assertDoesNotThrow(() -> sut.findByEmail(savedUser.email()));

        // then
        assertNotNull(findUser);
    }

    @Test
    @DisplayName("조회 예외 테스트")
    void getCurrentUserExceptionTest() {
        // given
        UserAccountInfo accountInfo = UserAccountInfo.builder()
                .username("Jacob")
                .email("jake@jake.jake")
                .password("jawbreaker")
                .build();

        User user = User.builder()
                .userAccountInfo(accountInfo)
                .bio("I like to skateboard")
                .image("https://i.stack.imgur.com/xHWG8.jpg")
                .build();

        // when, then
        assertThatThrownBy(() -> sut.findByEmail(user.email()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 유저가 없습니다.");
    }

    private User savedUser() {
        UserAccountInfo accountInfo = UserAccountInfo.builder()
                .email("jake@jake.jake")
                .username("Jacob")
                .password("jakejake")
                .build();

        User user = User.builder()
                .userAccountInfo(accountInfo)
                .build();

        return userRepository.save(user);
    }

}
