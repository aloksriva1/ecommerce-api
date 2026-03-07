package com.corporate.learning.auth_service.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;

}
