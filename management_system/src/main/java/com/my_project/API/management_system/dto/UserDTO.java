package com.my_project.API.management_system.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    //    private LocalDate lastLogin;
    private String email;
    private Long roleId;

}
