package com.my_project.API.management_system.config.security;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PermissionEnum {
    USER_WRITE("user:write"),  //Admin create users
    USER_READ("user:read"), // (view user) Admin
    USER_DELETE("user:delete"),
    USER_EDIT("user:edit"),



    LEAVE_WRITE("leave:write"), // Maker Request
    LEAVE_READ("leave:read"), // ADMIN,maker request, Approve
    LEAVE_EDIT("leave:edit"), //Maker request
    LEAVE_DELETE("leave:delete"), // ADMIN


    STATUS_REJECTED("approve:write"), //who approve by
    STATUS_APPROVED("approve:write");


    private String description;


}