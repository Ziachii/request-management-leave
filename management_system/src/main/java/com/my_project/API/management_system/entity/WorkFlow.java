package com.my_project.API.management_system.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "workflows")
public class WorkFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "WORKFLOW_TYPE")
    private String type;//  LEAVE, MISSION

    @ManyToOne
    private Role role;


}
