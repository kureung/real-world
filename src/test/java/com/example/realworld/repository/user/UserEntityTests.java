package com.example.realworld.repository.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.model.UserAccountInfo;
import org.junit.jupiter.api.Test;

class UserEntityTests {

    @Test
    void 유저_도메인_모델이_유저_엔티티로_변환된다() {
        UserEntity userEntity = UserEntity.convertedUserEntity(user());
        assertNotNull(userEntity);
    }

    @Test
    void 유저_엔티티가_유저_도메인_모델로_변환된다() {
        UserAccountInfoEmbed userAccountInfoEmbed = userAccountInfoEmbed();
        UserEntity userEntity = userEntity(userAccountInfoEmbed);
        User user = userEntity.convertedUserDomainModel();
        assertNotNull(user);
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

    private UserAccountInfoEmbed userAccountInfoEmbed() {
        return UserAccountInfoEmbed.builder()
                .email("abc@naver.com")
                .password("password")
                .username("username")
                .build();
    }

    private UserEntity userEntity(UserAccountInfoEmbed userAccountInfoEmbed) {
        return UserEntity.builder()
                .userAccountInfoEmbed(userAccountInfoEmbed)
                .image("abc@naver.com")
                .bio("I like a apple")
                .build();
    }

}