package com.application.oauth.repository.user.junit;

import com.application.oauth.model.Role;
import com.application.oauth.model.RoleEnum;
import com.application.oauth.model.User;
import com.application.oauth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryJunitTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void returnUserByUsernameTest() {
        //given
        Role role = new Role();
//        User user = new User(20L, "Slava", "Slava", Collections.singletonList(role));
        //when
        //then
    }

}