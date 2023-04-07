package com.application.oauth.service.impl;

import com.application.oauth.model.User;
import com.application.oauth.repository.UserRepository;
import com.application.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


}