package com.lovable.com.lovable.error;

// Custom exception for 404 resource-not-found conditions
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ResourceNotFoundException extends RuntimeException{
    String resourceName; // Entity/type missing
    String resourceId;   // Identifier that was not found
}
