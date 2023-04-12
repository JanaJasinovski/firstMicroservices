package com.application.oauth.controller;

import com.application.oauth.dto.UserDto;
import com.application.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/users")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> findAllUsers() {
        return userService.findAllUsers();
    }

}