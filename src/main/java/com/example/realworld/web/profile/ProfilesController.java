package com.example.realworld.web.profile;

import com.example.realworld.web.profile.dto.ProfileResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles/{username}")
public class ProfilesController {

    @GetMapping
    public ProfileResponse getProfile(@PathVariable String username) {
        
    }

    @PostMapping("/follow")
    public ProfileResponse followUser(@PathVariable String username) {

    }

    @DeleteMapping("/follow")
    public ProfileResponse unfollowUser(@PathVariable String username) {

    }
}
