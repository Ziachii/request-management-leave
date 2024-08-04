package com.my_project.API.management_system.mapper;

import com.my_project.API.management_system.dto.WorkFlowDTO;
import com.my_project.API.management_system.entity.WorkFlow;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface WorkFlowMapper {
    WorkFlowMapper INSTANCE = Mappers.getMapper(WorkFlowMapper.class);

    WorkFlow toWorkFlow(WorkFlowDTO workFlowDTO);
    WorkFlowDTO toWorkFlowDTO(WorkFlow workFlow);

}
