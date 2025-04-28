// UserResponse.java
package com.api.java_api.dtos;

import java.time.LocalDateTime;

public record UserResponse(
        String id,
        String username,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}