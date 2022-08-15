package com.example.realworld.web.user.dto;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.model.UserAccountInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

        @NotNull(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 양식이 일치하지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

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

        @NotBlank(message = "회원 이름을 입력해주세요.")
        private String username;

        @NotNull(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 양식이 일치하지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

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

    @Data
    @NoArgsConstructor
    @JsonTypeName("user")
    @JsonTypeInfo(use = NAME, include = WRAPPER_OBJECT)
    public static class UpdateUser {

        @NotNull(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 양식이 일치하지 않습니다.")
        private String email;

        private String bio;

        private String image;

        public User convertToDomainModel() {
            UserAccountInfo accountInfo = UserAccountInfo.builder()
                    .email(email)
                    .build();

            return User.builder()
                    .userAccountInfo(accountInfo)
                    .bio(bio)
                    .image(image)
                    .build();
        }

    }

}
