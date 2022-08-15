package com.example.realworld.web.user;

import com.example.realworld.domain.user.UserUseCase;
import com.example.realworld.domain.user.model.User;
import com.example.realworld.web.token.TokenFactory;
import com.example.realworld.web.user.dto.UserRequest;
import com.example.realworld.web.user.dto.UserResponse;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    private final TokenFactory tokenFactory;

    @PostMapping
    public UserResponse registration(@Valid @RequestBody UserRequest.Registration request) {
        User user = request.convertToDomainModel();
        String savedUserEmail = userUseCase.save(user);
        User savedUser = userUseCase.findByEmail(savedUserEmail);
        return UserResponse.convertToResponseDto(savedUser);
    }

    @PostMapping("/login")
    public UserResponse authentication(@Valid @RequestBody UserRequest.Authentication request,
                                       HttpServletResponse response
    ) {
        User user = request.convertToDomainModel();
        String loginEmail = userUseCase.login(user);
        User findUser = userUseCase.findByEmail(loginEmail);

        String token = tokenFactory.createdTokenByEmail(findUser.email());
        response.addHeader("token", token);
        return UserResponse.convertToResponseDto(findUser, token);
    }

}
