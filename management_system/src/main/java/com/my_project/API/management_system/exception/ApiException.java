package com.my_project.API.management_system.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@EqualsAndHashCode(callSuper=false)
@RequiredArgsConstructor
@Data
public class ApiException extends RuntimeException{
    private final HttpStatus status;
    private final String message;
}
