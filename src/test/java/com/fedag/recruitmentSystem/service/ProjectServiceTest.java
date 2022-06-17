package com.fedag.recruitmentSystem.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.fedag.recruitmentSystem.dto.request.ProjectRequest;
import com.fedag.recruitmentSystem.mapper.ProjectMapper;
import com.fedag.recruitmentSystem.model.Project;
import com.fedag.recruitmentSystem.repository.ProjectRepository;
import com.fedag.recruitmentSystem.service.impl.ProjectServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

  @Mock
  private ProjectRepository projectRepository;

  @InjectMocks
  private ProjectServiceImpl projectService;

  @Mock
  private ProjectMapper projectMapper;

  @Test
  void testGetAllProject() {
    projectService.getAllProjects();
    verify(projectRepository).findAll();
  }

  @Test
  void testGetAllProjectPageable() {
    Pageable pageable = Mockito.any(Pageable.class);
    projectService.getAllProjects(pageable);
    verify(projectRepository).findAll(pageable);
  }

  @Test
  void testGetProjectById() {
    when(projectRepository.findById(anyLong())).thenReturn(Optional.of(new Project()));
    projectService.findById(anyLong());
    verify(projectRepository).findById(anyLong());
  }

  @Test
  void testProjectSave() {
    ProjectRequest projectRequest = new ProjectRequest();
    projectService.save(projectRequest);
    Project project = projectMapper.dtoToModel(projectRequest);
    verify(projectRepository).save(project);
  }

  @Test
  void testDeleteProjectById() {
    projectService.deleteById(anyLong());
    verify(projectRepository).deleteById(anyLong());
  }
}
