package com.application.oauth.service;

import com.application.oauth.dto.UserDto;
import com.application.oauth.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    String getEmailById(Long id);

    UserDto getUser(Long id);
}