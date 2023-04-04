package com.application.oauth.service.impl;

import com.application.oauth.dto.BearerToken;
import com.application.oauth.dto.LoginDto;
import com.application.oauth.dto.RegisterDto;
import com.application.oauth.exception.RolenameNotFoundException;
import com.application.oauth.model.Role;
import com.application.oauth.model.RoleEnum;
import com.application.oauth.model.User;
import com.application.oauth.repository.RoleRepository;
import com.application.oauth.repository.UserRepository;
import com.application.oauth.security.TokenProvider;
import com.application.oauth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r.getRoleName()));
        return tokenProvider.generateToken(user.getUsername(), rolesNames);
    }

    @Override
    public User register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UsernameNotFoundException("User already exists with username: " + registerDto.getUsername());
        } else {
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            Role role = roleRepository.findByName(RoleEnum.USER).orElseThrow(() -> new RolenameNotFoundException("Role name not found"));
            user.setRoles(Collections.singletonList(role));
//            String token = tokenProvider.generateToken(registerDto.getUsername(), Collections.singletonList(role.getRoleName()));
            return  userRepository.save(user); /*new BearerToken(token, "Bearer ");*/
        }
    }
}
