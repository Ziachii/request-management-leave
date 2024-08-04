package com.my_project.API.management_system.service;

import com.my_project.API.management_system.dto.UserDTO;
import com.my_project.API.management_system.entity.Role;
import com.my_project.API.management_system.entity.User;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService
{
     User createUser(UserDTO userDTO);
     User getUserById(Long id);
     List<UserDTO> getAllUsers();
//    Page<UserDTO> getUsersWithPagination(int page, int size);
     User updateUser(Long id, UserDTO userDTO);
     void deleteUser(Long id);
     List<User> filterUser(Map<String, String> params);
     Page<User> userPage(Map<String, String> params);


}
