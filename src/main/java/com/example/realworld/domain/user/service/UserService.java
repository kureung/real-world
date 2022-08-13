package com.example.realworld.domain.user.service;

import com.example.realworld.domain.user.UserRepository;
import com.example.realworld.domain.user.UserUseCase;
import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.passwordencoder.PasswordEncryption.FakePasswordEncryption;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepository userRepository;
    private final FakePasswordEncryption passwordEncryption;
//    private final PasswordEncryption passwordEncryption;

    @Override
    public User save(User user) {
        String encodedPassword = passwordEncryption.encode(user.password());
        user.changeEncodedPassword(encodedPassword);
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
        if (!passwordEncryption.matches(user.password(), findUser.password())) {
            throw new IllegalStateException("비밀번호가 틀립니다.");
        }

        return findUser.email();
    }

}
