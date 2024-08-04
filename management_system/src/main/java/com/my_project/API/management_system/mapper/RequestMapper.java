package com.my_project.API.management_system.mapper;

import com.my_project.API.management_system.dto.RequestDTO;
import com.my_project.API.management_system.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    RequestDTO toRequestDTO(Request request);

    Request toRequest(RequestDTO requestDTO);

}
