package com.example.realworld.repository.user;

import static lombok.AccessLevel.PROTECTED;

import com.example.realworld.domain.user.model.UserAccountInfo;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class UserAccountInfoEmbed {

    private String username;

    private String email;

    private String password;

    @Builder
    private UserAccountInfoEmbed(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserAccountInfo convertToDomainModel() {
        return UserAccountInfo.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
    }

    public static UserAccountInfoEmbed convertToEntity(UserAccountInfo userAccountInfo) {
        return UserAccountInfoEmbed.builder()
                .username(userAccountInfo.username())
                .email(userAccountInfo.email())
                .password(userAccountInfo.password())
                .build();
    }

}
