package com.application.oauth.service.impl;

import com.application.oauth.dto.UserDto;
import com.application.oauth.model.Role;
import com.application.oauth.model.User;
import com.application.oauth.repository.UserRepository;
import com.application.oauth.service.UserService;
import com.application.oauth.utils.DtoConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DtoConvert dtoConvert;

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: users) {
            List<Role> roles = user.getRoles();
            for (Role role: roles) {
                userDtos.add(new UserDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), role.getRoleName()));
            }
        }
        return userDtos;
    }

    @Override
    public String getEmailById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getEmail();
    }

    @Override
    public UserDto getUser(Long id) {
        return dtoConvert.convertToUserDto(userRepository.findById(id).orElseThrow());
    }
}