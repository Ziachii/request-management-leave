package com.my_project.API.management_system.exception;

import org.springframework.http.HttpStatus;


public class ResourceCannotCreateException extends ApiException{
    public ResourceCannotCreateException(String resourceName, String resourceValue) {
        super(HttpStatus.BAD_REQUEST, String.format("%s = %s is already existed", resourceName, resourceValue));
    }
}
