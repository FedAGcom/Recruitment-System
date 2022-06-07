package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.ExperienceResponse;
import com.fedag.recruitmentSystem.dto.ResumeRequest;
import com.fedag.recruitmentSystem.dto.ResumeResponse;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.ExperienceMapper;
import com.fedag.recruitmentSystem.mapper.ResumeMapper;
import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.repository.ResumeRepository;
import com.fedag.recruitmentSystem.service.ResumeService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeServiceImpl implements ResumeService<ResumeResponse, ResumeRequest> {

  private final ResumeRepository resumeRepository;
  private final ResumeMapper resumeMapper;
  private final ExperienceMapper experienceMapper;

  @Override
  public List<ResumeResponse> getAllResumes() {
    return resumeMapper.modelToDto(resumeRepository.findAll());
  }
  
  @Override
  public Page<ResumeResponse> getAllResumes(Pageable pageable) {
    return resumeMapper.modelToDto(resumeRepository.findAll(pageable));
  }

  @Override
  public ResumeResponse findById(Long id) throws ObjectNotFoundException {
    Resume resume = resumeRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Resume with id: " + id + " not found"));
    return resumeMapper.modelToDto(resume);
  }

  public Page<ResumeResponse> getAllResumesByPosition(String position, Pageable pageable) {
      return resumeMapper.modelToDto(resumeRepository.findByPosition(position, pageable));
  }

  @Override
  public List<ExperienceResponse> listExperiencesByResume(Long resumeId) throws ObjectNotFoundException {
    ResumeResponse resumeResponse = findById(resumeId);
    Resume resume = resumeMapper.dtoToModel(resumeResponse);
    List<Experience> listExperiences = resume.getExperiences();
    listExperiences.forEach(e->e.setResume(resume));
    return experienceMapper.modelToDto(listExperiences);
  }

  @Override
  public void save(ResumeRequest element) {
    Resume resume = resumeMapper.dtoToModel(element);
    if(resume.getId()!=null) {
      resume.getExperiences()
              .forEach(e->e.setResume(resume));
    }
    resumeRepository.save(resume);
  }

  @Override
  public void deleteById(Long id) {
    resumeRepository.deleteById(id);
  }

  public List<ResumeResponse> findByDateCreated(LocalDateTime dateCreated) {
    return resumeMapper.modelToDto(resumeRepository.findByDateCreated(dateCreated));
  }
  
  @Override
  public Page<ResumeResponse> findByTextFilter(String text, Pageable pageable) {
    return resumeMapper.modelToDto(resumeRepository.findByTextFilter(text, pageable));
  }

  @Override
  public Page<ResumeResponse> findByPosition(String position, Pageable pageable) {
    return resumeMapper.modelToDto(resumeRepository.findByPosition(position, pageable));
  }
}
