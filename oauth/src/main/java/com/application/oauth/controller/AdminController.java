package com.application.oauth.controller;

import com.application.oauth.dto.UserDto;
import com.application.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/user/{id}")
    public String findUser(@PathVariable Long id) {
        return userService.getEmailById(id);
    }

    @GetMapping("/userById/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

}