package com.lovable.com.lovable.service;

import com.lovable.com.lovable.dto.auth.AuthResponse;
import com.lovable.com.lovable.dto.auth.LoginRequest;
import com.lovable.com.lovable.dto.auth.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
