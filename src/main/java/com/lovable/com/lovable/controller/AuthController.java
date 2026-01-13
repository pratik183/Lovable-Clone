package com.lovable.com.lovable.controller;

import com.lovable.com.lovable.dto.auth.AuthResponse;
import com.lovable.com.lovable.dto.auth.LoginRequest;
import com.lovable.com.lovable.dto.auth.SignupRequest;
import com.lovable.com.lovable.dto.auth.UserProfileResponse;
import com.lovable.com.lovable.service.AuthService;
import com.lovable.com.lovable.service.UserService;
import jakarta.persistence.Access;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

    AuthService authService; // Handles signup/login flows and token issuance
    UserService userService; // Reads user profile information

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request){
        return ResponseEntity.ok(authService.signup(request)); // Register new user and return auth tokens
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> signup(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request)); // Authenticate existing user and return auth tokens
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(){
        Long userId = 1L; // TODO: replace with authenticated user id when security is wired
        return ResponseEntity.ok(userService.getProfile(userId)); // Fetch current user's profile
    }

}
