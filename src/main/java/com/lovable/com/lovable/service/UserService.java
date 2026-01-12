package com.lovable.com.lovable.service;

import com.lovable.com.lovable.dto.auth.UserProfileResponse;

public interface UserService {

    UserProfileResponse getProfile(Long userId);
}
