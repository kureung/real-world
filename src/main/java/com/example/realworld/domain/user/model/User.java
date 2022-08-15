package com.example.realworld.domain.user.model;

import com.example.realworld.domain.user.exception.DuplicatedFollowingUserException;
import com.example.realworld.web.exception.ErrorCode;
import java.util.Collection;
import java.util.Set;
import lombok.Builder;
import lombok.ToString;

@ToString
public class User {

    private final UserAccountInfo userAccountInfo;

    private String bio;

    private String image;

    private final Set<String> followingEmails;

    @Builder
    private User(UserAccountInfo userAccountInfo, String bio, String image, Set<String> followingEmails) {
        this.userAccountInfo = userAccountInfo;
        this.bio = bio;
        this.image = image;
        this.followingEmails = followingEmails;
    }

    public UserAccountInfo accountInfo() {
        return userAccountInfo;
    }

    public String bio() {
        return bio;
    }

    public String image() {
        return image;
    }

    public String email() {
        return userAccountInfo.email();
    }

    public String username() {
        return userAccountInfo.username();
    }

    public String password() {
        return userAccountInfo.password();
    }

    public User update(User newUser) {
        this.bio = newUser.bio;
        this.image = newUser.image();
        this.userAccountInfo.changeEmail(newUser.email());
        return this;
    }

    public void changeEncodedPassword(String password) {
        userAccountInfo.changeEncodedPassword(password);
    }

    public void follow(User followedUser) {
        verifyDuplicateFollowEmail(followedUser);
        followingEmails.add(followedUser.email());
    }

    public Collection<String> followingEmails() {
        return followingEmails.stream()
                .toList();
    }

    private void verifyDuplicateFollowEmail(User followedUser) {
        if (followingEmails.contains(followedUser.email())) {
            throw new DuplicatedFollowingUserException(ErrorCode.DuplicatedFollowingUser);
        }
    }

    public void unfollow(User followedUser) {
        followingEmails.remove(followedUser.email());
    }

}
