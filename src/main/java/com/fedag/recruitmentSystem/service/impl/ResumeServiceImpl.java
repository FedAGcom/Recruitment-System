package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.ResumeUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ExperienceResponseForAdmin;
import com.fedag.recruitmentSystem.dto.request.ResumeRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ResumeResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.ExperienceResponseForUser;
import com.fedag.recruitmentSystem.dto.response.user_response.ResumeResponseForUser;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.ExperienceMapper;
import com.fedag.recruitmentSystem.mapper.ResumeMapper;
import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.repository.ExperienceRepository;
import com.fedag.recruitmentSystem.repository.ResumeRepository;
import com.fedag.recruitmentSystem.service.ResumeService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeServiceImpl implements ResumeService<ResumeResponseForAdmin,
        ResumeRequest, ResumeUpdateRequest> {

  private final ResumeRepository resumeRepository;
  private final ExperienceRepository experienceRepository;
  private final ResumeMapper resumeMapper;
  private final ExperienceMapper experienceMapper;

  @Override
  public List<ResumeResponseForAdmin> getAllResumes() {
    return resumeMapper.modelToDto(resumeRepository.findAll());
  }
  
  @Override
  public Page<ResumeResponseForAdmin> getAllResumes(Pageable pageable) {
    return resumeMapper.modelToDto(resumeRepository.findAll(pageable));
  }

  @Override
  public ResumeResponseForAdmin findById(Long id) throws ObjectNotFoundException {
    Resume resume = resumeRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Resume with id: " + id + " not found"));
    return resumeMapper.modelToDto(resume);
  }

  @Override
  public Page<ResumeResponseForAdmin> getAllResumesByPosition(String position, Pageable pageable) {
      return resumeMapper.modelToDto(resumeRepository.findByPosition(position, pageable));
  }

  @Override
  public List<ExperienceResponseForAdmin> listExperiencesByResume(Long resumeId) throws ObjectNotFoundException {
    ResumeResponseForAdmin resumeResponseForAdmin = findById(resumeId);
    Resume resume = resumeMapper.dtoToModel(resumeResponseForAdmin);
    List<Experience> listExperiences = resume.getExperiences();
    listExperiences.forEach(e->e.setResume(resume));
    return experienceMapper.modelToDto(listExperiences);
  }

  @Override
  @Transactional
  public void save(ResumeRequest element) {
    Resume resume = resumeMapper.dtoToModel(element);
    Resume resumeRes = resumeRepository.save(resume);
    resume.getExperiences()
            .forEach(e-> {
              e.setResume(resumeRes);
              experienceRepository.save(e);
            });
  }

  @Override
  @Transactional
  public void update(ResumeUpdateRequest element) {
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

  @Override
  public List<ResumeResponseForAdmin> findByDateCreated() {
    return resumeMapper.modelToDto(resumeRepository.findByDateCreated());
  }
  
  @Override
  public Page<ResumeResponseForAdmin> findByTextFilter(String text, Pageable pageable) {
    return resumeMapper.modelToDto(resumeRepository.findByTextFilter(text, pageable));
  }

  @Override
  public Page<ResumeResponseForUser> getAllResumesByPositionForUser(String position, Pageable pageable) {
    return resumeMapper.modelToDtoForUser(resumeRepository.findByPosition(position, pageable));
  }

  @Override
  public Page<ResumeResponseForUser> getAllResumesForUser(Pageable pageable) {
    return resumeMapper.modelToDtoForUser(resumeRepository.findAll(pageable));
  }

  @Override
  public Page<ResumeResponseForUser> findByTextFilterForUser(String text, Pageable pageable) {
    return resumeMapper.modelToDtoForUser(resumeRepository.findByTextFilter(text, pageable));
  }

  @Override
  public ResumeResponseForUser findByIdForUser(Long id) {
    Resume resume = resumeRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Resume with id: " + id + " not found"));
    return resumeMapper.modelToDtoForUser(resume);
  }

  @Override
  public List<ExperienceResponseForUser> listExperiencesByResumeForUser(Long resumeId) {
    ResumeResponseForAdmin resumeResponseForAdmin = findById(resumeId);
    Resume resume = resumeMapper.dtoToModel(resumeResponseForAdmin);
    List<Experience> listExperiences = resume.getExperiences();
    listExperiences.forEach(e->e.setResume(resume));
    return experienceMapper.modelToDtoForUser(listExperiences);
  }

  @Override
  public List<ResumeResponseForUser> findByDateCreatedForUser() {
    return resumeMapper.modelToDtoForUser(resumeRepository.findByDateCreated());
  }
}
