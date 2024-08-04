package com.my_project.API.management_system.mapper;

import com.my_project.API.management_system.dto.UserDTO;
import com.my_project.API.management_system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(source = "roleId", target = "role.id")
    User toUser(UserDTO userDTO);

    @Mapping(source = "role.id", target = "roleId")
    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOs(List<User> users);
}
