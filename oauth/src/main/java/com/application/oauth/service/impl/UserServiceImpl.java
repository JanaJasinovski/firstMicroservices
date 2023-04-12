package com.application.oauth.service.impl;

import com.application.oauth.dto.UserDto;
import com.application.oauth.model.Role;
import com.application.oauth.model.User;
import com.application.oauth.repository.UserRepository;
import com.application.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: users) {
            List<Role> roles = user.getRoles();
            for (Role role: roles) {
                userDtos.add(new UserDto(user.getId(), user.getUsername(), role.getRoleName()));
            }
        }
        return userDtos;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        String roleName = null;
        for (Role role : user.getRoles()) {
            roleName = role.getRoleName();
        }
        return new UserDto(user.getId(), user.getUsername(), roleName);
    }
}