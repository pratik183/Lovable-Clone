package com.lovable.com.lovable.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse user
    ) {
}
