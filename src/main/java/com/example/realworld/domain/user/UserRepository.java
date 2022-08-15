package com.example.realworld.domain.user;

import com.example.realworld.domain.user.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Slf4j
    class UserFakeRepository implements UserRepository {

        private final Map<String, User> store = new HashMap<>();

        private UserFakeRepository() {
        }

        public static UserRepository instance() {
            return UserFakeRepository.RepositoryHolder.INSTANCE;
        }

        @Override
        public User save(User user) {
            store.put(user.email(), user);
            return user;
        }

        @Override
        public Optional<User> findByEmail(String email) {
            if (!store.containsKey(email)) {
                return Optional.empty();
            }

            return Optional.of(store.get(email));
        }

        @Override
        public boolean existsByEmail(String email) {
            return store.containsKey(email);
        }

        public void clear() {
            store.clear();
        }

        private static class RepositoryHolder {

            private static final UserRepository INSTANCE = new UserFakeRepository();

        }

    }

}
