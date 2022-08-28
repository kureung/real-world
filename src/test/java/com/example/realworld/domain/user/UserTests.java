package com.example.realworld.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.model.UserAccountInfo;
import org.junit.jupiter.api.Test;

class UserTests {

    @Test
    void updateUserTest() {
        // given
        UserAccountInfo accountInfo = UserAccountInfo.builder()
                .username("name")
                .email("abc@naver.com")
                .password("password")
                .build();

        User user = User.builder()
                .userAccountInfo(accountInfo)
                .bio("I like an apple")
                .image("www.naver.com")
                .build();

        UserAccountInfo newAccountInfo = UserAccountInfo.builder()
                .email("email@naver.com")
                .build();

        User newUser = User.builder()
                .userAccountInfo(newAccountInfo)
                .bio("I like a banana")
                .image("www.google.com")
                .build();

        // when
        User updatedUser = assertDoesNotThrow(() -> user.update(newUser));

        // then
        assertAll(
                () -> assertThat(updatedUser.bio()).isEqualTo(newUser.bio()),
                () -> assertThat(updatedUser.image()).isEqualTo(newUser.image()),
                () -> assertThat(updatedUser.email()).isEqualTo(newUser.email())
        );
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
                .build();
    }

    private User anotheruser() {
        UserAccountInfo newAccountInfo = UserAccountInfo.builder()
                .username("name2")
                .email("email@naver.com")
                .password("password2")
                .build();

        return User.builder()
                .userAccountInfo(newAccountInfo)
                .bio("I like a banana")
                .image("www.google.com")
                .build();
    }

}