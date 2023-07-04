package com.application.oauth.service.user.junit;

import com.application.oauth.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserServiceJunitTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllUsersTest() {

    }

    @Test
    void getUsernameByIdTest() {
        String username = userService.getEmailById(20L);
        System.out.println(username);
    }
}