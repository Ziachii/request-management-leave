package com.my_project.API.management_system.service.impl;
import com.my_project.API.management_system.dto.UserDTO;
import com.my_project.API.management_system.entity.Role;
import com.my_project.API.management_system.entity.User;
import com.my_project.API.management_system.exception.ResourceCannotCreateException;
import com.my_project.API.management_system.exception.ResourceNotFoundException;
import com.my_project.API.management_system.mapper.UserMapper;
import com.my_project.API.management_system.repository.RoleRepository;
import com.my_project.API.management_system.repository.UserRepository;
import com.my_project.API.management_system.service.UserService;
import com.my_project.API.management_system.spec.UserFilter;
import com.my_project.API.management_system.spec.UserSpec;
import com.my_project.API.management_system.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDTO userDTO) {
        Optional<User> byEmail = userRepository.findByEmail(userDTO.getEmail());
        if(byEmail.isPresent()) {
            throw new ResourceCannotCreateException("Email", userDTO.getEmail());
        }

        Optional<Role> role = roleRepository.findById(userDTO.getRoleId());
        if (role.isEmpty()) {
            throw new ResourceNotFoundException("Role", userDTO.getRoleId());
        }
        User user = UserMapper.INSTANCE.toUser(userDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role.get());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
      return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User", id));
    }

//    @Override
//    public Page<UserDTO> getUsersWithPagination(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<User> userPage = userRepository.findAll(pageable);
//
//        // Convert Page<User> to Page<UserDTO>
//        return userPage.map(user -> new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName()));
//    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.toUserDTOs(users);
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser = getUserById(id);

        // Update fields from UserDTO
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setFullName(userDTO.getFullName());
        existingUser.setEmail(userDTO.getEmail());

        // Always update the password with the new one from UserDTO, if provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()) ); // Directly set the password
        }

        // Update role if provided (assuming role is managed by ID)
        if (userDTO.getRoleId() != null) {
            Role role = roleRepository.findById(userDTO.getRoleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Role", userDTO.getRoleId()));
            existingUser.setRole(role);
        }

        // Save the updated user entity
        return userRepository.save(existingUser);
     }

    @Override
    public void deleteUser(Long id) {
        User userById = getUserById(id);
        userRepository.delete(userById);
    }

    @Override
    public List<User> filterUser(Map<String, String> params) {
        UserFilter userFilter = new UserFilter();

        if(params.containsKey("username")){
            String name = params.get("username");
            userFilter.setUsername(name);
        }

        //int pageLimit = PageableUtils
        UserSpec userSpec = new UserSpec(userFilter);


        return userRepository.findAll(userSpec);
    }


    @Override
    public Page<User> userPage(Map<String, String> params) {
        UserFilter userFilter = new UserFilter();

        if(params.containsKey("username")){
            String name = params.get("username");
            userFilter.setUsername(name);
        }

        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }
        UserSpec userSpec = new UserSpec(userFilter);
        Pageable pageable = PageUtil.getPageable(pageNumber,pageLimit);

        Page<User> page = userRepository.findAll(userSpec, pageable);
        return page;
    }

    @Override
    public boolean authenticateUser(User user) {
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser.isPresent() && passwordEncoder.matches(user.getPassword(), foundUser.get().getPassword())) {
            return true;
        }
        return false;
    }

//    @Override
//    public boolean registerUser(User user) {
//        Optional<User> foundUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
//        if (foundUser.isPresent()) {
//            return false; // User already exists
//        }
//        // Hash the password before saving
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }


}
