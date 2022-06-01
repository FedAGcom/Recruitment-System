package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.service.impl.ExperienceServiceImpl;
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
@RequestMapping("/api/experiences")
public class ExperienceController {

  private final ExperienceServiceImpl experienceService;

  @GetMapping
  public Page<Experience> getAllResumes(@PageableDefault(size = 5) Pageable pageable) {
    return experienceService.getAllExperience(pageable);
  }

  @GetMapping("/{id}")
  public Experience getById(@PathVariable Long id) {
    return experienceService.findById(id);
  }

  @PostMapping
  public void addVacancy(@RequestBody Experience experience) {
    experienceService.save(experience);
  }

  @PutMapping
  public void updateVacancy(@RequestBody Experience experience) {
    experienceService.save(experience);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    experienceService.deleteById(id);
  }
}
