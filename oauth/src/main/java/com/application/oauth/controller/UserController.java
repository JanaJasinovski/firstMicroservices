package com.application.oauth.controller;

import com.application.oauth.dto.UserDto;
import com.application.oauth.model.User;
import com.application.oauth.repository.UserRepository;
import com.application.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/user" )
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public UserDto findUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
