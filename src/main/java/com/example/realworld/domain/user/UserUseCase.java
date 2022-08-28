package com.example.realworld.domain.user;

import com.example.realworld.domain.user.model.User;

public interface UserUseCase {

    String save(User user);

    User findByEmail(String email);

    String login(User user);

    String update(String userEmail, User newUser);

    void follow(User owner, User followingUser);

    void unfollow(User owner, User followingUser);

}
