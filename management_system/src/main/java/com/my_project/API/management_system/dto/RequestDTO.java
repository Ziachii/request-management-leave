package com.my_project.API.management_system.dto;

import lombok.Data;

@Data
public class RequestDTO {
    private Long id;
    private String type;
    private Long workflowId;
}
