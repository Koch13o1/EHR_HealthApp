package com.kochhealth.user_service.dto;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String fullName;
    private String email;
    private String password;
    private String role;

    private Long caregiverId;  // Only for SENIOR role
}
