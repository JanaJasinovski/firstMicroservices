package com.application.oauth.service;

import com.application.oauth.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    UserDto getUserByUsername(String username);
}