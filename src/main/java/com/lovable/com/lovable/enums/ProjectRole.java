package com.lovable.com.lovable.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.lovable.com.lovable.enums.ProjectPermission.*;

@RequiredArgsConstructor
@Getter
public enum ProjectRole {

    // Can view and edit; also delete (no member management)
    EDITOR(Set.of(VIEW, EDIT, DELETE, VIEW_MEMBERS)),
    // Can only view project
    VIEWER(Set.of(VIEW, VIEW_MEMBERS)),
    // Full control: view/edit/delete and manage members
    OWNER(Set.of(VIEW, EDIT, DELETE, MANAGE_MEMBERS, VIEW_MEMBERS));

    private final Set<ProjectPermission> permissions; // Capabilities granted to this role

    // above is 1 way to define roles and their permissions and another way is to make a constructor with varargs like below
    // private final Set<ProjectPermission> permissions;
    // ProjectRole(ProjectPermission... permissions) {
    //     this.permissions = Set.of(permissions);
    // }
    // EDITOR(VIEW, EDIT, DELETE),
}
