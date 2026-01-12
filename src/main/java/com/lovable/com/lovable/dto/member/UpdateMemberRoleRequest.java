package com.lovable.com.lovable.dto.member;

import com.lovable.com.lovable.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull  ProjectRole role) {
}
