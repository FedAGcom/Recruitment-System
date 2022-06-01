package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.service.impl.ExamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exams")
public class ExamController {

  private final ExamServiceImpl examService;

  @GetMapping
  public Page<Exam> showAllExams(@PageableDefault(size = 5) Pageable pageable) {
    return examService.getAllExams(pageable);
  }

  @GetMapping("/{id}")
  public Exam getExam(@PathVariable Long id) {
    return examService.findById(id);
  }

  @PostMapping
  public void addNewExam(@RequestBody Exam exam) {
    examService.save(exam);
  }

  @PutMapping
  public void updateExam(@RequestBody Exam exam) {
    examService.save(exam);
  }

  @DeleteMapping("/{id}")
  public void deleteExam(@PathVariable Long id) {
    examService.deleteById(id);
  }
}
