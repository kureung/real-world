package com.example.realworld.web.user;

import com.example.realworld.domain.user.UserUseCase;
import com.example.realworld.domain.user.model.User;
import com.example.realworld.web.user.dto.UserRequest;
import com.example.realworld.web.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final UserUseCase userUseCase;

    @PostMapping
    public UserResponse registration (@RequestBody UserRequest.Registration request) {
        User user = request.convertToDomainModel();
        User savedUser = userUseCase.save(user);
        return UserResponse.convertToResponseDto(savedUser);
    }

    @PostMapping("/login")
    public UserResponse authentication(@RequestBody UserRequest.Authentication request) {
        User user = request.convertToDomainModel();
        String loginEmail = userUseCase.login(user);
        User findUser = userUseCase.findByEmail(loginEmail);
        return UserResponse.convertToResponseDto(findUser);
    }

}
