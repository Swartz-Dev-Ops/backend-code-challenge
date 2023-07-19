package com.midwesttape.project.challengeapplication.rest;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/v1/users/{userId}")
    public User user(@PathVariable final Long userId) {
        return userService.user(userId);
    }

    @PutMapping("/v1/users/{userId}")
    public User updateUser(@PathVariable final Long userId, @RequestBody final User updatedUser) {
        User user = userService.user(userId);

        if (user != null) {
            if (updatedUser.getFirstName() != null){
                user.setFirstName(updatedUser.getFirstName());
            }

            if (updatedUser.getLastName() != null){
                user.setLastName(updatedUser.getLastName());
            }

            if (updatedUser.getUsername() != null){
                user.setUsername(updatedUser.getUsername());
            }

            if (updatedUser.getPassword() != null){
                user.setPassword(updatedUser.getPassword());
            }

            userService.updateUser(user);
        }

        return user;
    }

}
