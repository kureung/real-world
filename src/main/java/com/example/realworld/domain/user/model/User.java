package com.example.realworld.domain.user.model;

import lombok.Builder;
import lombok.ToString;

@ToString
public class User {

    private final UserAccountInfo userAccountInfo;

    private String bio;

    private String image;

    @Builder
    private User(UserAccountInfo userAccountInfo, String bio, String image) {
        this.userAccountInfo = userAccountInfo;
        this.bio = bio;
        this.image = image;
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

}
