package com.application.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;

    public UserDto(Long userId, String email, String role) {
        this.id = userId;
        this.email = email;
        this.role = role;
    }
}
