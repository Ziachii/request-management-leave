package com.my_project.API.management_system.service;

import com.my_project.API.management_system.dto.RoleDTO;
import com.my_project.API.management_system.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    public Role createRole(Role role);
    public Role getRole(Long id);
    public List<Role> getAllRoles();
    public Role updateRole(Long id, RoleDTO roleDTO);
}
