package com.example.realworld.domain.user.model;

import lombok.Builder;
import lombok.ToString;

@ToString
public class UserAccountInfo {

    private String username;

    private String email;

    private String password;

    @Builder
    private UserAccountInfo(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changeEncodedPassword(String password) {
        this.password = password;
    }

}
