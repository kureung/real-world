package com.example.realworld.domain.follow.model;

import static com.example.realworld.web.exception.ErrorCode.DUPLICATED_FOLLOWING_USER;
import static com.example.realworld.web.exception.ErrorCode.NOT_FOUND_FOLLOWING_USER;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.realworld.domain.follow.exception.FollowException;
import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.model.UserAccountInfo;
import java.util.HashSet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FollowTests {

    @Test
    void 팔로우를_조회할_때_팔로우하지_않는_유저라면_거짓이_나온다() {
        Follow follow = new Follow(new HashSet<>());
        User user = user();
        assertFalse(follow.isFollowing(user));
    }

    @Test
    void 팔로우를_조회할_때_팔로우한_유저라면_참이_나온다() {
        Follow follow = new Follow(new HashSet<>());
        User user = user();
        follow.follow(user);
        assertTrue(follow.isFollowing(user));
    }

    @Test
    void 이미_팔로우한_유저를_또_다시_팔로우_할_경우_예외가_발생한다() {
        Follow follow = new Follow(new HashSet<>());
        User user = user();
        follow.follow(user);
        Assertions.assertThatThrownBy(() -> follow.follow(user))
                .isInstanceOf(FollowException.class)
                .hasMessage(DUPLICATED_FOLLOWING_USER.message());
    }

    @Test
    void 팔로우한_유저를_팔로우_해제할_때_팔로우를_조회하면_거짓이_나온다() {
        Follow follow = new Follow(new HashSet<>());
        User user = user();
        follow.follow(user);
        follow.unfollow(user);
        assertFalse(follow.isFollowing(user));
    }

    @Test
    void 팔로우하지_않는_유저를_팔로우_해제할_때_예외가_발생한다() {
        Follow follow = new Follow(new HashSet<>());
        User user = user();
        Assertions.assertThatThrownBy(() -> follow.unfollow(user))
                .isInstanceOf(FollowException.class)
                .hasMessage(NOT_FOUND_FOLLOWING_USER.message());
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

}