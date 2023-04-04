package com.application.oauth.service.impl;

import com.application.oauth.dto.UserDto;
import com.application.oauth.model.Role;
import com.application.oauth.model.RoleEnum;
import com.application.oauth.model.User;
import com.application.oauth.repository.UserRepository;
import com.application.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllAdmins() {
        List<User> admins = new ArrayList<>();
        List<User> users = findAllUsers();
        for (User user : users) {
            List<Role> roles = user.getRoles();
            for (Role role: roles) {
                if(role.getRoleName().equals(RoleEnum.ADMIN.name())){
                    admins.add(user);
                }
            }
        }
        return admins;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String roleName = null;

        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            roleName = role.getRoleName();
        }
        return new UserDto(user.getId(), username, roleName);
    }
}