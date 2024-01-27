package com.library.management.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
}