package com.lovable.com.lovable.controller;

import com.lovable.com.lovable.dto.member.InviteMemberRequest;
import com.lovable.com.lovable.dto.member.MemberResponse;
import com.lovable.com.lovable.dto.member.UpdateMemberRoleRequest;
import com.lovable.com.lovable.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/members")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService; // Handles invites, role changes, and removals with access checks

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getProjectMember(@PathVariable Long projectId){
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId)); // List members for a project
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(
        @PathVariable Long projectId,
        @RequestBody @Valid InviteMemberRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.inviteMember(projectId, request) // Create/invite a new project member
        );
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId,
            @RequestBody @Valid UpdateMemberRoleRequest request
    ){
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, request)); // Adjust member permissions via role change
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long projectId, @PathVariable Long memberId){
        projectMemberService.removeProjectMember(projectId, memberId); // Remove member from project
        return ResponseEntity.noContent().build();
    }
}
