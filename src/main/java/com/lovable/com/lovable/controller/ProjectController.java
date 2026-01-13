package com.lovable.com.lovable.controller;

import com.lovable.com.lovable.dto.project.ProjectRequest;
import com.lovable.com.lovable.dto.project.ProjectResponse;
import com.lovable.com.lovable.dto.project.ProjectSummaryResponse;
import com.lovable.com.lovable.dto.subscription.PlanResponse;
import com.lovable.com.lovable.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService; // Orchestrates project CRUD with ownership checks

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProjects() {
        return ResponseEntity.ok(projectService.getUserProjects()); // List projects current user can access
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getUserProjectById(id)); // Fetch a single accessible project
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request)); // Create project for current user
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequest request) {
        return ResponseEntity.ok(projectService.updateProject(id, request)); // Update project fields if user has permission
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.softDelete(id); // Soft-delete to retain history but hide from listings
        return ResponseEntity.noContent().build();
    }
}
