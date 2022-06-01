package com.fedag.recruitmentSystem.service.impl;


import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.repository.ExamRepository;
import com.fedag.recruitmentSystem.service.ExamService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService<Exam> {

  private final ExamRepository examRepository;

  @Override
  public List<Exam> getAllExams() {
    return examRepository.findAll();
  }

  @Override
  public Page<Exam> getAllExams(Pageable pageable) {
    return examRepository.findAll(pageable);
  }

  @Override
  public Exam findById(Long id) {
    return examRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Exam with id: " + id + " not found")
        );
  }

  @Override
  public void save(Exam element) {
    examRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    examRepository.deleteById(id);
  }
}
