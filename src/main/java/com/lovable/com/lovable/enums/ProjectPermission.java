package com.lovable.com.lovable.enums;

// Enum of granular actions that can be granted on a project
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProjectPermission {
    // Allows read-only access to project contents
    VIEW("project:view"),
    // Allows editing project assets/configuration
    EDIT("project:edit"),
    // Allows deleting a project
    DELETE("project:delete"),
    // Allows inviting/removing members or changing their roles
    MANAGE_MEMBERS("project_members:manage"),
    // Allows viewing the list of project members
    VIEW_MEMBERS("project_members:view");

    private final String value; // Permission string stored/checked externally (e.g., in tokens)
}
