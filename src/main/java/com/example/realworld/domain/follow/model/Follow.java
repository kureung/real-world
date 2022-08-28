package com.example.realworld.domain.follow.model;

import static com.example.realworld.web.exception.ErrorCode.DUPLICATED_FOLLOWING_USER;
import static com.example.realworld.web.exception.ErrorCode.NOT_FOUND_FOLLOWING_USER;

import com.example.realworld.domain.follow.exception.FollowException;
import com.example.realworld.domain.user.model.User;
import java.util.Set;

public class Follow {

    private final Set<User> followingUsers;

    public Follow(Set<User> followingUsers) {
        this.followingUsers = followingUsers;
    }

    public void follow(User user) {
        verifyFollowDuplicate(user);
        followingUsers.add(user);
    }

    private void verifyFollowDuplicate(User user) {
        if (isFollowing(user)) {
            throw new FollowException(DUPLICATED_FOLLOWING_USER);
        }
    }

    public void unfollow(User user) {
        verifyNotFoundUser(user);
        followingUsers.remove(user);
    }

    private void verifyNotFoundUser(User user) {
        if (!isFollowing(user)) {
            throw new FollowException(NOT_FOUND_FOLLOWING_USER);
        }
    }

    public boolean isFollowing(User user) {
        return followingUsers.contains(user);
    }

}
