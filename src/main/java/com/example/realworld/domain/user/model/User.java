package com.example.realworld.domain.user.model;

import com.example.realworld.domain.follow.model.Follow;
import java.util.Objects;
import lombok.Builder;
import lombok.ToString;

@ToString
public class User {

    private final UserAccountInfo userAccountInfo;

    private String bio;

    private String image;

    private final Follow follow;

    @Builder
    private User(UserAccountInfo userAccountInfo, String bio, String image, Follow follow) {
        this.userAccountInfo = userAccountInfo;
        this.bio = bio;
        this.image = image;
        this.follow = follow;
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

    public void follow(User user) {
        follow.follow(user);
    }

    public void unfollow(User followedUser) {
        follow.unfollow(followedUser);
    }

    public boolean isFollowing(User followedUser) {
        return follow.isFollowing(followedUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userAccountInfo, user.userAccountInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccountInfo);
    }

}
