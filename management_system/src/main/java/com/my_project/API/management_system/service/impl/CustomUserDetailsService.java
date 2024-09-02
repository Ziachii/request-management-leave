package com.my_project.API.management_system.service.impl;

import com.my_project.API.management_system.config.security.RoleEnum;
import com.my_project.API.management_system.entity.User;
import com.my_project.API.management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user by email
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // Map role ID to RoleEnum
        RoleEnum roleEnum = getRoleEnum(user.getRole().getId());

        // Return UserDetails with authorities
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                roleEnum.getAuthorities()
        );
    }

    // Method to map role ID to RoleEnum
    private RoleEnum getRoleEnum(Long roleId) {
        // Adjust this logic if necessary, ensure that roleId is correctly mapped to enum
        for (RoleEnum role : RoleEnum.values()) {
            if (role.getId() == roleId) { // Assuming roleEnum contains ID
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role ID: " + roleId);
    }
}
