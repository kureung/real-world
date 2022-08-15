package com.example.realworld.domain.user.service;

import com.example.realworld.domain.user.UserRepository;
import com.example.realworld.domain.user.UserUseCase;
import com.example.realworld.domain.user.exception.DuplicatedEmailException;
import com.example.realworld.domain.user.exception.NotFoundUserException;
import com.example.realworld.domain.user.exception.NotValidLoginException;
import com.example.realworld.domain.user.model.User;
import com.example.realworld.domain.user.passwordencoder.PasswordEncryption;
import com.example.realworld.web.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    private final PasswordEncryption passwordEncryption;

    @Override
    @Transactional
    public String save(User user) {
        if (userRepository.existsByEmail(user.email())) {
            throw new DuplicatedEmailException(ErrorCode.DUPLICATED_EMAIL);
        }

        String encodedPassword = passwordEncryption.encode(user.password());
        user.changeEncodedPassword(encodedPassword);
        return userRepository.save(user).email();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NO_SUCH_USER_ELEMENT));
    }

    @Override
    public String login(User user) {
        User findUser = findUserForLogin(user);
        verifyPassword(user, findUser);
        return findUser.email();
    }

    private void verifyPassword(User user, User findUser) {
        if (!passwordEncryption.matches(user.password(), findUser.password())) {
            throw new NotValidLoginException(ErrorCode.Not_Valid_Login);
        }
    }

    private User findUserForLogin(User user) {
        return userRepository.findByEmail(user.email())
                .orElseThrow(() -> new NotValidLoginException(ErrorCode.Not_Valid_Login));
    }

    @Override
    @Transactional
    public String update(String userEmail, User newUser) {
        User user = findByEmail(userEmail);
        user.update(newUser);
        return user.email();
    }

}
