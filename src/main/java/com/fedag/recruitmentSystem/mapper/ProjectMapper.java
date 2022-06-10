package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.ProjectRequest;
import com.fedag.recruitmentSystem.dto.request.ProjectUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.ProjectResponse;
import com.fedag.recruitmentSystem.model.Project;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final ModelMapper mapper;

    public ProjectResponse modelToDto(Project project) {
        return mapper.map(project, ProjectResponse.class);
    }

    public List<ProjectResponse> modelToDto(List<Project> project) {
        return project
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<ProjectResponse> modelToDto(Page<Project> projectPage) {
        return projectPage
                .map(new Function<Project, ProjectResponse>() {
                    @Override
                    public ProjectResponse apply(Project entity) {
                        return modelToDto(entity);
                    }
                });
    }

    public Project dtoToModel(ProjectRequest dto) {
        return mapper.map(dto, Project.class);
    }

    public Project dtoToModel(ProjectUpdateRequest dto) {
        return mapper.map(dto, Project.class);
    }

    public Project dtoToModel(ProjectResponse dto) {
        return mapper.map(dto, Project.class);
    }

    public List<Project> dtoToModel(List<ProjectResponse> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
