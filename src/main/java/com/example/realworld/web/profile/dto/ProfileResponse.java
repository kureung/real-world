package com.example.realworld.web.profile.dto;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.example.realworld.domain.user.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonTypeName("profile")
@JsonTypeInfo(use = NAME, include = WRAPPER_OBJECT)
public class ProfileResponse {

    private String username;

    private String bio;

    private String image;

    private boolean following;

    @Builder
    private ProfileResponse(String username, String bio, String image, boolean following) {
        this.username = username;
        this.bio = bio;
        this.image = image;
        this.following = following;
    }

    public static ProfileResponse convertedResponseDto(User user) {
        return ProfileResponse.builder()
                .username(user.username())
                .bio(user.bio())
                .image(user.image())
                .build();
    }

    public static ProfileResponse convertedResponseDto(User owner, User anotherUser) {
        return ProfileResponse.builder()
                .username(anotherUser.username())
                .bio(anotherUser.bio())
                .following(owner.isFollowing(anotherUser))
                .image(anotherUser.image())
                .build();
    }

}
