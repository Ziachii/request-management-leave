package com.my_project.API.management_system.controller;
import com.my_project.API.management_system.dto.UserDTO;
import com.my_project.API.management_system.entity.User;
import com.my_project.API.management_system.mapper.UserMapper;
import com.my_project.API.management_system.service.UserService;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {



    private String jwtSecretKey;

    private String jwtIssuer;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        UserDTO responseDTO = UserMapper.INSTANCE.toUserDTO(user);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User userById = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.INSTANCE.toUserDTO(userById));
    }

/*

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
*/
    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userService.updateUser(id, userDTO);
        UserDTO responseDTO = UserMapper.INSTANCE.toUserDTO(user);
        return ResponseEntity.ok(responseDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping()
    public ResponseEntity<?> filterUsers(@RequestParam Map<String, String> params) {
        List<UserDTO> list = userService.filterUser(params)
                .stream()
                .map(UserMapper.INSTANCE::toUserDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
/*

    @GetMapping("/page")
    public ResponseEntity<?> userPage(@RequestParam Map<String, String> params){
        Page<User> page = userService.userPage(params);

        PageDTO pageDTO = new PageDTO(page);
        return ResponseEntity.ok(pageDTO);
    }
*/
/*@PostMapping("/register")
public ResponseEntity<String> register(@RequestBody User user) {
    System.out.println("Received user: " + user);
    boolean isRegistered = userService.registerUser(user);
    if (isRegistered) {
        return ResponseEntity.ok("User registered successfully");
    } else {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username or email already exists");
    }
}*/

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//
//        boolean isAuthenticated = userService.authenticateUser(user);
//        if (isAuthenticated) {
//            return ResponseEntity.ok("User logged in successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or Password is incorrect");
//        }
//    }
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        if (authentication != null) {
//            new SecurityContextLogoutHandler().logout(request, response, authentication);
//            return ResponseEntity.ok("Logged out successfully");
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
//    }




    private String createJwtToken(User user) {

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(24 * 3600))
                .subject(user.getUsername())
                .claim("role", user.getRole())
                .build();
        var encoder = new NimbusJwtEncoder(
                new ImmutableSecret<>(jwtSecretKey.getBytes()));
        var params = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return encoder.encode(params).getTokenValue();
    }
}
