package com.lovable.com.lovable.mapper;

import com.lovable.com.lovable.dto.auth.SignupRequest;
import com.lovable.com.lovable.dto.auth.UserProfileResponse;
import com.lovable.com.lovable.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest); // Convert signup DTO to User entity

    UserProfileResponse toUserProfileResponse(User user); // Convert User entity to profile response
}
