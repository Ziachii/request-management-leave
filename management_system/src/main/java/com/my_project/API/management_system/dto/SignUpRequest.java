package com.my_project.API.management_system.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String username;
    private String password;
    private String fullName;
    private Long roleId;

}
