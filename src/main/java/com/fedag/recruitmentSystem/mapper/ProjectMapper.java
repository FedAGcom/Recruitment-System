package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.ProjectRequest;
import com.fedag.recruitmentSystem.dto.request.ProjectUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ProjectResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.ProjectResponseForUser;
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

    public ProjectResponseForAdmin modelToDto(Project project) {
        return mapper.map(project, ProjectResponseForAdmin.class);
    }

    public List<ProjectResponseForAdmin> modelToDto(List<Project> project) {
        return project
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<ProjectResponseForAdmin> modelToDto(Page<Project> projectPage) {
        return projectPage
                .map(new Function<Project, ProjectResponseForAdmin>() {
                    @Override
                    public ProjectResponseForAdmin apply(Project entity) {
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

    public Project dtoToModel(ProjectResponseForAdmin dto) {
        return mapper.map(dto, Project.class);
    }

    public List<Project> dtoToModel(List<ProjectResponseForAdmin> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

    public Page<ProjectResponseForUser> modelToDtoForUser(Page<Project> projectPage) {
        return projectPage
                .map(new Function<Project, ProjectResponseForUser>() {
                    @Override
                    public ProjectResponseForUser apply(Project entity) {
                        return modelToDtoForUser(entity);
                    }
                });
    }

    public ProjectResponseForUser modelToDtoForUser(Project project) {
        return mapper.map(project, ProjectResponseForUser.class);
    }
}
