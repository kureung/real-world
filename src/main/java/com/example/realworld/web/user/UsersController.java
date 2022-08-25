package com.example.realworld.web.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

//    private final UserUseCase userUseCase;
//
//    private final JwtConfig jwtConfig = new JwtConfig();
//
//    @PostMapping
//    public UserResponse registration(@Valid @RequestBody UserRequest.Registration request) {
//        User user = request.convertToDomainModel();
//        String savedUserEmail = userUseCase.save(user);
//        User savedUser = userUseCase.findByEmail(savedUserEmail);
//        return UserResponse.convertToResponseDto(savedUser);
//    }

//    @PostMapping("/login")
//    public UserResponse authentication(@Valid @RequestBody UserRequest.Authentication request,
//                                       HttpServletResponse response
//    ) {
//        User user = request.convertToDomainModel();
//        String loginEmail = userUseCase.login(user);
//        User findUser = userUseCase.findByEmail(loginEmail);
//
//        Token jwtToken = new JwtToken(, findUser.email());
//        String tokenValue = jwtToken.createdTokenByEmail();
//        response.addHeader("token", tokenValue);
//        return UserResponse.convertToResponseDto(findUser, tokenValue);
//    }
}
