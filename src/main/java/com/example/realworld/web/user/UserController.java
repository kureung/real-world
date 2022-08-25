//package com.example.realworld.web.user;
//
//import com.example.realworld.domain.user.UserUseCase;
//import com.example.realworld.jwt.KureungToken;
//import com.example.realworld.jwt.config.Jwt;
//import com.example.realworld.jwt.config.JwtKureungConfig;
//import com.example.realworld.web.token.Token;
//import com.example.realworld.web.token.TokenParser;
//import com.example.realworld.web.user.dto.UserRequest;
//import com.example.realworld.web.user.dto.UserResponse;
//import javax.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/user")
//public class UserController {
//
//    private final UserUseCase userUseCase;
//    private final TokenParser tokenParser;
//
//    @GetMapping
//    public UserResponse getCurrentUser(@RequestHeader String token) {
//        new KureungToken(token).userEmail(new JwtKureungConfig());
////        String email = tokenParser.findEmailByToken(token);
////        User user = userUseCase.findByEmail(email);
////        return UserResponse.convertToResponseDto(user, token);
//    }
//
//    @PutMapping
//    public UserResponse updateUser(@Valid @RequestBody UserRequest.UpdateUser request,
//                                   @Jwt Token token
//    ) {
//        String findUserEmail = tokenParser.findEmailByToken(token);
//        String updatedUserEmail = userUseCase.update(findUserEmail, request.convertToDomainModel());
//        return UserResponse.convertToResponseDto(userUseCase.findByEmail(updatedUserEmail), token);
//    }
//
//}
