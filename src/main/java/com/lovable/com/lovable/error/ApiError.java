package com.lovable.com.lovable.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

// DTO describing error responses returned to clients
public record ApiError(
        HttpStatus status, // HTTP status to return
        String message, // Human-readable message
        Instant timestamp, // Time error was generated
        @JsonInclude(JsonInclude.Include.NON_NULL) List<ApiFieldError> errors // Field-level validation errors (if any)
) {
    public ApiError(HttpStatus status, String message) {
        this(status, message, Instant.now(), null); // Convenience constructor without field errors
    }

    public ApiError(HttpStatus status, String message, List<ApiFieldError> errors) {
        this(status, message, Instant.now(), errors); // Convenience constructor with field errors
    }
}

// Field-specific validation error details
record ApiFieldError(
        String field,
        String message
){}
