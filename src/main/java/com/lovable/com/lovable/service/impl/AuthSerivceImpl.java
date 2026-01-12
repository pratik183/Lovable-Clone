package com.lovable.com.lovable.service.impl;

import com.lovable.com.lovable.dto.auth.AuthResponse;
import com.lovable.com.lovable.dto.auth.LoginRequest;
import com.lovable.com.lovable.dto.auth.SignupRequest;
import com.lovable.com.lovable.entity.User;
import com.lovable.com.lovable.error.BadRequestException;
import com.lovable.com.lovable.mapper.UserMapper;
import com.lovable.com.lovable.repository.UserRepository;
import com.lovable.com.lovable.security.AuthUtil;
import com.lovable.com.lovable.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthSerivceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthUtil authUtil;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException("Username is already in use with username: " + request.username());
        });

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        String token = authUtil.generateAccessToken(user);
        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()) // Authenticate the user
        );

        User user = (User) authentication.getPrincipal(); // Cast to your User entity
        String token = authUtil.generateAccessToken(user);
        return new AuthResponse(token, userMapper.toUserProfileResponse(user)); // Return AuthResponse with token and user profile
    }
}
