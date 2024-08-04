package com.my_project.API.management_system.service.impl;

import com.my_project.API.management_system.dto.RoleDTO;
import com.my_project.API.management_system.entity.Role;
import com.my_project.API.management_system.exception.ResourceNotFoundException;
import com.my_project.API.management_system.repository.RoleRepository;
import com.my_project.API.management_system.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Role", id));
    }


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Long id, RoleDTO roleDTO) {
        Role getRole = getRole(id);
        getRole.setId(id);
        getRole.setName(roleDTO.getName());
        return roleRepository.save(getRole);
    }
}
