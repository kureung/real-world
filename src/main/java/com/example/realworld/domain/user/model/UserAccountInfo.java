package com.example.realworld.domain.user.model;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountInfo that = (UserAccountInfo) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}
