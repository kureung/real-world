package com.example.realworld.web.profile;

import com.example.realworld.domain.user.UserUseCase;
import com.example.realworld.domain.user.model.User;
import com.example.realworld.web.profile.dto.ProfileResponse;
import com.example.realworld.web.token.TokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles/{userEmail}")
public class ProfilesController {

    private final UserUseCase userUseCase;

    private final TokenParser tokenParser;

    @GetMapping
    public ProfileResponse getProfile(@PathVariable String userEmail) {
        User user = userUseCase.findByEmail(userEmail);
        return ProfileResponse.convertedResponseDto(user);
    }

    @PostMapping("/follow")
    public ProfileResponse followUser(@RequestHeader String token, @PathVariable String userEmail) {
        String ownerUserEmail = tokenParser.findEmailByToken(token);
        User ownerUser = userUseCase.findByEmail(ownerUserEmail);
        User followingUser = userUseCase.findByEmail(userEmail);
        userUseCase.follow(ownerUser, followingUser);
        return ProfileResponse.convertedResponseDto(ownerUser, followingUser);
    }

    @DeleteMapping("/follow")
    public ProfileResponse unfollowUser(@RequestHeader String token, @PathVariable String userEmail) {
        return null;
    }
}
