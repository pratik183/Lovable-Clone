package com.lovable.com.lovable.dto.member;

import com.lovable.com.lovable.enums.ProjectRole;

import java.time.Instant;

public record
MemberResponse(
        Long userId,
        String username,
        String name,
        ProjectRole projectRole,
        Instant invitedAt
) {
}
