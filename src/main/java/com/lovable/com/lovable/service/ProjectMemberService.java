package com.lovable.com.lovable.service;

import com.lovable.com.lovable.dto.member.InviteMemberRequest;
import com.lovable.com.lovable.dto.member.MemberResponse;
import com.lovable.com.lovable.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    void removeProjectMember(Long projectId, Long memberId);
}
