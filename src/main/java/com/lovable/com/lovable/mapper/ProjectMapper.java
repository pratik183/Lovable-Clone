package com.lovable.com.lovable.mapper;

import com.lovable.com.lovable.dto.project.ProjectResponse;
import com.lovable.com.lovable.dto.project.ProjectSummaryResponse;
import com.lovable.com.lovable.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);
}
