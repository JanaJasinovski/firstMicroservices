package com.application.oauth.utils;

import com.application.oauth.dto.UserDto;
import com.application.oauth.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoConvert {
    private final ModelMapper modelMapper;

    public UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
