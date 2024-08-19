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
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        RoleEnum roleEnum = RoleEnum.values()[(int) (user.getRole().getId() - 1)]; // Assuming role ID starts from 1 and matches enum order

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                roleEnum.getAuthorities()
        );
    }
}