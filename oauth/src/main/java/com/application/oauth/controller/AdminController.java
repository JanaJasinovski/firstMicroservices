package com.application.oauth.controller;

import com.application.oauth.model.User;
import com.application.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean isUserAdmin(@PathVariable String username) {
        List<User> admins = userService.findAllAdmins();
        for(User user: admins) {
            if(Objects.equals(user.getUsername(), username)) {
                return true;
            }
        }
        return false;
    }
}