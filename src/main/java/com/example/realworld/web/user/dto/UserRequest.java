package com.example.realworld.web.user.dto;


import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.model.UserAccountInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeName("user")
public final class UserRequest {

    @Data
    @NoArgsConstructor
    @JsonTypeName("user")
    @JsonTypeInfo(use = NAME, include = WRAPPER_OBJECT)
    public static final class Authentication {

        private String email;

        private String password;

        @Builder
        private Authentication(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public User convertToDomainModel() {
            UserAccountInfo accountInfo = UserAccountInfo.builder()
                    .email(email)
                    .password(password)
                    .build();

            return User.builder()
                    .userAccountInfo(accountInfo)
                    .build();
        }

    }



    @Data
    @NoArgsConstructor
    @JsonTypeName("user")
    @JsonTypeInfo(use = NAME, include = WRAPPER_OBJECT)
    public static class Registration {

        private String username;

        private String email;

        private String password;

        @Builder
        private Registration(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public User convertToDomainModel() {
            UserAccountInfo accountInfo = UserAccountInfo.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .build();

            return User.builder()
                    .userAccountInfo(accountInfo)
                    .build();
        }

    }


}
