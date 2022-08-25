package com.example.realworld.web.user.dto;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.example.realworld.domain.user.model.User;
import com.example.realworld.web.token.Token;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonTypeName("user")
@JsonTypeInfo(use = NAME, include = WRAPPER_OBJECT)
public final class UserResponse {

    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

    @Builder
    private UserResponse(String email, String token, String username, String bio, String image) {
        this.email = email;
        this.token = token;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public static UserResponse convertToResponseDto(User user) {
        return UserResponse.builder()
                .email(user.email())
                .username(user.username())
                .bio(user.bio())
                .image(user.image())
                .build();
    }

    public static UserResponse convertToResponseDto(User user, Token token) {
        return UserResponse.builder()
                .email(user.email())
                .username(user.username())
                .bio(user.bio())
                .image(user.image())
                .token("")
                .build();
    }

}
