package com.lovable.com.lovable.service.impl;

import com.lovable.com.lovable.dto.project.ProjectRequest;
import com.lovable.com.lovable.dto.project.ProjectResponse;
import com.lovable.com.lovable.dto.project.ProjectSummaryResponse;
import com.lovable.com.lovable.entity.Project;
import com.lovable.com.lovable.entity.ProjectMember;
import com.lovable.com.lovable.entity.ProjectMemberId;
import com.lovable.com.lovable.entity.User;
import com.lovable.com.lovable.enums.ProjectRole;
import com.lovable.com.lovable.error.ResourceNotFoundException;
import com.lovable.com.lovable.mapper.ProjectMapper;
import com.lovable.com.lovable.repository.ProjectMemberRepository;
import com.lovable.com.lovable.repository.ProjectRepository;
import com.lovable.com.lovable.repository.UserRepository;
import com.lovable.com.lovable.security.AuthUtil;
import com.lovable.com.lovable.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        // User owner = userRepository.findById(userId)... // Alternative eager fetch
        User owner = userRepository.getReferenceById(userId); // Proxy reference avoids extra select

        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();

        project = projectRepository.save(project); // Persist project first to get id

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();
       projectMemberRepository.save(projectMember); // Link owner as member with OWNER role

       return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authUtil.getCurrentUserId();
        var projects = projectRepository.findAllAccessibleByUser(userId); // Access-checked query
        return projectMapper.toListOfProjectSummaryResponse(projects); // Map to lightweight summaries
    }

    @Override
    @PreAuthorize("@security.canViewProject(#projectId)")
    public ProjectResponse getUserProjectById(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId); // Enforce access at service level too
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canEditProject(#projectId)")
    public ProjectResponse updateProject(Long projectId, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId); // Only allow updates if accessible

        project.setName(request.name());
        project = projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#projectId)")
    public void softDelete(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId); // Ensure owner/member can delete

        project.setDeletedAt(Instant.now()); // Soft delete preserves data
        projectRepository.save(project);
    }

    ///  INTERNAL FUNCTIONS

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId.toString())); // Centralized access + not-found handling
    }
}
