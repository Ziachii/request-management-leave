package com.my_project.API.management_system.controller;
import com.my_project.API.management_system.dto.RoleDTO;
import com.my_project.API.management_system.dto.UserDTO;
import com.my_project.API.management_system.entity.Role;
import com.my_project.API.management_system.entity.User;
import com.my_project.API.management_system.mapper.RoleMapper;
import com.my_project.API.management_system.mapper.UserMapper;
import com.my_project.API.management_system.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping()
    public ResponseEntity<?> createRole(@RequestBody RoleDTO roleDTO) {
        Role role = RoleMapper.INSTANCE.toRole(roleDTO);
        role = roleService.createRole(role);
        return ResponseEntity.ok(RoleMapper.INSTANCE.toRoleDTO(role));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        Role role = roleService.getRole(id);
        return ResponseEntity.ok(RoleMapper.INSTANCE.toRoleDTO(role));
    }


    @GetMapping()
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        Role role = roleService.updateRole(id, roleDTO);
        RoleDTO responseDTO = RoleMapper.INSTANCE.toRoleDTO(role);
        return ResponseEntity.ok(responseDTO);
    }

}
