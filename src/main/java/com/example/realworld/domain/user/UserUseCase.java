package com.example.realworld.domain.user;

import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.UserRepository.UserFakeRepository;
import java.util.NoSuchElementException;
import java.util.Objects;
public interface UserUseCase {

    User save(User user);

    User findByEmail(String email);

    String login(User user);

    class UserFakeUseCase implements UserUseCase {

        private static class UserFakUseCaseHolder {

            private static final UserUseCase INSTANCE = new UserFakeUseCase(UserFakeRepository.instance());

        }

        public static UserUseCase instance() {
            return UserFakUseCaseHolder.INSTANCE;
        }

        private final UserRepository userRepository;

        private UserFakeUseCase(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public User save(User user) {
            return userRepository.save(user);
        }

        @Override
        public User findByEmail(String email) {
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new NoSuchElementException("해당 유저가 없습니다."));
        }

        @Override
        public String login(User user) {
            User findUser = findByEmail(user.email());
            if (!Objects.equals(findUser.password(), user.password())) {
                throw new IllegalStateException("비밀번호가 틀립니다.");
            }
            return findUser.email();
        }

    }

}
