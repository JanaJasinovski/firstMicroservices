package com.application.oauth.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String sub;
    private String firstName;
    private String lastName;
    private String password;
}
