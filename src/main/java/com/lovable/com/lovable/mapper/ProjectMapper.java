package com.lovable.com.lovable.mapper;

import com.lovable.com.lovable.dto.project.ProjectResponse;
import com.lovable.com.lovable.dto.project.ProjectSummaryResponse;
import com.lovable.com.lovable.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project); // Full project detail mapping

    ProjectSummaryResponse toProjectSummaryResponse(Project project); // Lightweight summary mapping

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects); // Bulk map list to summaries
}
