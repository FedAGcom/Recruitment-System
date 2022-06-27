package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.ProjectRequest;
import com.fedag.recruitmentSystem.dto.request.ProjectUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ProjectResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.ProjectResponseForUser;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.ProjectMapper;
import com.fedag.recruitmentSystem.model.Project;
import com.fedag.recruitmentSystem.repository.ProjectRepository;
import com.fedag.recruitmentSystem.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService<ProjectResponseForAdmin,
        ProjectRequest, ProjectUpdateRequest> {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public List<ProjectResponseForAdmin> getAllProjects() {
        return projectMapper.modelToDto(projectRepository.findAll());
    }

    @Override
    public Page<ProjectResponseForAdmin> getAllProjects(Pageable pageable) {
        return projectMapper.modelToDto(projectRepository.findAll(pageable));
    }

    @Override
    public ProjectResponseForAdmin findById(Long id) {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Project with id: " + id + " not found")
                );
        return projectMapper.modelToDto(project);
    }

    @Override
    public void save(ProjectRequest element) {

        Project project = projectMapper.dtoToModel(element);

        projectRepository.save(project);
    }

    @Override
    public void update(ProjectUpdateRequest element) {
        Project project = projectMapper.dtoToModel(element);
        projectRepository.save(project);
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Page<ProjectResponseForUser> getAllProjectsForUser(Pageable pageable) {
        return projectMapper.modelToDtoForUser(projectRepository.findAll(pageable));
    }

    @Override
    public ProjectResponseForUser findByIdForUser(Long id) {
        Project project = projectRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Project with id: " + id + " not found")
                );
        return projectMapper.modelToDtoForUser(project);
    }
}
