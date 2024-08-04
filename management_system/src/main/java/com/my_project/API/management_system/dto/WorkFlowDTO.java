package com.my_project.API.management_system.dto;

import com.my_project.API.management_system.entity.User;
import lombok.Data;

import java.util.List;


@Data
public class WorkFlowDTO {
    private int id;
    private User user;
    private String type;
    private List<Long> approveIds;
}
