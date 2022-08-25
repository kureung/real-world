package com.example.realworld.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.realworld.domain.user.UserRepository.UserFakeRepository;
import com.example.realworld.domain.user.exception.DuplicatedEmailException;
import com.example.realworld.domain.user.exception.NotFoundUserException;
import com.example.realworld.domain.user.exception.NotValidLoginException;
import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.model.UserAccountInfo;
import com.example.realworld.domain.user.passwordencoder.PasswordEncryption.FakePasswordEncryption;
import com.example.realworld.domain.user.service.UserService;
import com.example.realworld.web.exception.ErrorCode;
import java.util.HashSet;
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
        User savedUser = savedUser();

        UserAccountInfo loginAccountInfo = UserAccountInfo.builder()
                .email(savedUser.email())
                .password(savedUser.password())
                .build();
        User loginUser = User.builder()
                .userAccountInfo(loginAccountInfo)
                .build();

        String loginUserEmail = assertDoesNotThrow(() -> sut.login(loginUser));

        assertNotNull(loginUserEmail);
    }

    @Test
    @DisplayName("로그인 예외 테스트")
    void authenticationExceptionTest() {
        User savedUser = savedUser();

        UserAccountInfo accountInfo = UserAccountInfo.builder()
                .email(savedUser.email())
                .password(savedUser.password() + "1")
                .build();
        User loginUser = User.builder()
                .userAccountInfo(accountInfo)
                .build();

        assertThatThrownBy(() -> sut.login(loginUser))
                .isInstanceOf(NotValidLoginException.class)
                .hasMessage(ErrorCode.NOT_VALID_LOGIN.message());
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

        String savedUserEmail = assertDoesNotThrow(() -> sut.save(user));

        assertNotNull(savedUserEmail);
    }

    @Test
    @DisplayName("회원가입 중복 예외 테스트")
    void registrationExceptionTest() {
        UserAccountInfo accountInfo = UserAccountInfo.builder()
                .username("Jacob")
                .email("jake@jake.jake")
                .password("jakejake")
                .build();

        User user1 = User.builder()
                .userAccountInfo(accountInfo)
                .build();

        assertDoesNotThrow(() -> userRepository.save(user1));

        User user2 = User.builder()
                .userAccountInfo(accountInfo)
                .build();

        assertThatThrownBy(() -> sut.save(user2))
                .isInstanceOf(DuplicatedEmailException.class)
                .hasMessage(ErrorCode.DUPLICATED_EMAIL.message());
    }

    @Test
    @DisplayName("조회 테스트")
    void getCurrentUserTest() {
        User savedUser = savedUser();
        User findUser = assertDoesNotThrow(() -> sut.findByEmail(savedUser.email()));
        assertNotNull(findUser);
    }

    @Test
    @DisplayName("조회 예외 테스트")
    void getCurrentUserExceptionTest() {
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

        assertThatThrownBy(() -> sut.findByEmail(user.email()))
                .isInstanceOf(NotFoundUserException.class)
                .hasMessage(ErrorCode.NO_SUCH_USER_ELEMENT.message());
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
