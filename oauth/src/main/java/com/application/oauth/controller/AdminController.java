package com.application.oauth.controller;

import com.application.oauth.model.User;
import com.application.oauth.security.TokenProvider;
import com.application.oauth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final TokenProvider tokenProvider;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/token")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getToken(HttpServletRequest request) {
        return tokenProvider.getToken(request);
    }

}