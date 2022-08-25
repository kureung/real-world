package com.example.realworld.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.example.realworld.domain.user.exception.DuplicatedFollowingUserException;
import com.example.realworld.domain.user.exception.NotFoundFollowingException;
import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.model.UserAccountInfo;
import com.example.realworld.web.exception.ErrorCode;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTests {

    @Test
    void updateUserTest() {
        User user = user();
        User newUser = anotherUser();

        User updatedUser = assertDoesNotThrow(() -> user.update(newUser));

        assertAll(
                () -> assertThat(updatedUser.bio()).isEqualTo(newUser.bio()),
                () -> assertThat(updatedUser.image()).isEqualTo(newUser.image()),
                () -> assertThat(updatedUser.email()).isEqualTo(newUser.email())
        );
    }

    @Test
    @DisplayName("팔로우 하기")
    void followingTest() {
        User user = user();
        User followedUser = anotherUser();

        user.follow(followedUser);

        assertThat(user.followingEmails()).hasSize(1);
    }

    @Test
    @DisplayName("이미 팔로우한 유저를 또 팔로우할 때 예외 발생")
    void followingExceptionTest() {
        User user = user();
        User followedUser = anotherUser();

        user.follow(followedUser);

        assertThatThrownBy(() -> user.follow(followedUser))
                .isInstanceOf(DuplicatedFollowingUserException.class)
                .hasMessage(ErrorCode.DUPLICATED_FOLLOWING_USER.message());
    }

    @Test
    @DisplayName("팔로우 해제 기능")
    void unfollowUserTest() {
        User user = user();
        User followedUser = anotherUser();
        user.follow(followedUser);

        user.unfollow(followedUser);

        assertThat(user.followingEmails()).isEmpty();
    }

    @Test
    @DisplayName("팔로우 안한 유저를 팔로우 해제시 예외 발생")
    void unfollowUserDuplicateExceptionTest() {
        User user = user();
        User followedUser = anotherUser();
        user.follow(followedUser);
        user.unfollow(followedUser);

        assertThatThrownBy(() -> user.unfollow(followedUser))
                .isInstanceOf(NotFoundFollowingException.class)
                .hasMessage(ErrorCode.NOT_FOUND_FOLLOWING.message());
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

    private User anotherUser() {
        UserAccountInfo newAccountInfo = UserAccountInfo.builder()
                .username("name2")
                .email("email@naver.com")
                .password("password2")
                .build();

        return User.builder()
                .userAccountInfo(newAccountInfo)
                .bio("I like a banana")
                .image("www.google.com")
                .followingEmails(new HashSet<>())
                .build();
    }

}