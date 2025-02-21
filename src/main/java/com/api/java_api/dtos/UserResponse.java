// UserResponse.java
package com.api.java_api.dtos;

public record UserResponse(
        String id,
        String username,
        String email
) {}