package com.example.realworld.domain.follow;

import com.example.realworld.domain.follow.model.Follow;
import com.example.realworld.domain.user.model.User;

public interface FollowRepository {

    Long save(User owner, User followingUser);

    void delete(User owner, User followingUser);

    Follow findByOwner(User user);

    class FollowFakeRepository implements FollowRepository {

        @Override
        public Long save(User owner, User followingUser) {
            return 1L;
        }

        @Override
        public void delete(User owner, User followingUser) {

        }

        @Override
        public Follow findByOwner(User user) {
            return null;
        }

    }

}
