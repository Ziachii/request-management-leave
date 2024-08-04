package com.my_project.API.management_system.controller;
import com.my_project.API.management_system.dto.PageDTO;
import com.my_project.API.management_system.dto.UserDTO;
import com.my_project.API.management_system.entity.User;
import com.my_project.API.management_system.mapper.UserMapper;
import com.my_project.API.management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

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

//    @GetMapping("/page")
//    public ResponseEntity<?> userPage(@RequestParam Map<String, String> params){
//        Page<User> page = userService.userPage(params);
//
//        PageDTO pageDTO = new PageDTO(page);
//        return ResponseEntity.ok(pageDTO);
//    }

}
