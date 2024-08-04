package com.my_project.API.management_system.mapper;

import com.my_project.API.management_system.dto.RoleDTO;
import com.my_project.API.management_system.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role toRole(RoleDTO roleDTO);
    RoleDTO toRoleDTO(Role role);

}
