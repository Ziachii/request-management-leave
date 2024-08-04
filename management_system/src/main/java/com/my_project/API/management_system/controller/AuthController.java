/*
package com.my_project.API.management_system.controller;

import com.my_project.API.management_system.dto.LoginRequest;
import com.my_project.API.management_system.dto.SignUpRequest;
import com.my_project.API.management_system.dto.UserDTO;
import com.my_project.API.management_system.payload.ApiResponse;
import com.my_project.API.management_system.payload.JwtAuthenticationResponse;
import com.my_project.API.management_system.security.JwtTokenProvider;
import com.my_project.API.management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Email Address already in use!"));
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(signUpRequest.getEmail());
        userDTO.setUsername(signUpRequest.getUsername());
        userDTO.setPassword(signUpRequest.getPassword());
        userDTO.setFullName(signUpRequest.getFullName());
        userDTO.setRoleId(signUpRequest.getRoleId());

        userService.createUser(userDTO);

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }
}
*/