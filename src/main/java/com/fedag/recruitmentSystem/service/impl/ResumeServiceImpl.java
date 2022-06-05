package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.repository.ResumeRepository;
import com.fedag.recruitmentSystem.service.ResumeService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService<Resume> {

  private final ResumeRepository resumeRepository;

  @Override
  public List<Resume> getAllResumes() {
    return resumeRepository.findAll();
  }
  
  @Override
  public Page<Resume> getAllResumes(Pageable pageable) {
    return resumeRepository.findAll(pageable);
  }

  @Override
  public Page<Resume> getAllResumesByPosition(String position, Pageable pageable) {
      return resumeRepository.findAllByResumeName(position, pageable);
  }
  
  @Override
  public Resume findById(Long id) {
    return resumeRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Resume with id: " + id + " not found"));
  }

  @Override
  public void save(Resume resume) {
    resumeRepository.save(resume);
  }

  @Override
  public void deleteById(Long id) {
    resumeRepository.deleteById(id);
  }

  public List<Resume> findByDateCreated(LocalDateTime dateCreated) {
    return resumeRepository.findByDateCreated(dateCreated);
  
  @Override
  public Page<Resume> findByTextFilter(String text, Pageable pageable) {
    return resumeRepository.findByTextFilter(text, pageable);
  }
}
