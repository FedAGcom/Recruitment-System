package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.service.impl.ResumeServiceImpl;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

  private final ResumeServiceImpl resumeService;

  @GetMapping
  public Page<Resume> getAllResumes(@PageableDefault(size = 5) Pageable pageable) {
    return resumeService.getAllResumes(pageable);
  }

  @GetMapping("/{id}")
  public Resume getById(@PathVariable Long id) {
    return resumeService.findById(id);
  }

  @PostMapping
  public void addVacancy(@RequestBody Resume resume) {
    resumeService.save(resume);
  }

  @PutMapping
  public void updateVacancy(@RequestBody Resume resume) {
    resumeService.save(resume);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    resumeService.deleteById(id);
  }
}
