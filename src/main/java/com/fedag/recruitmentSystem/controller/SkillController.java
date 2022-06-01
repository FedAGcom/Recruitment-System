package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Skill;
import com.fedag.recruitmentSystem.service.impl.SkillServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/skills")
public class SkillController {

  private final SkillServiceImpl skillService;

  @GetMapping
  public Page<Skill> getAllSkills(@PageableDefault(size = 5) Pageable pageable) {
    return skillService.getAllSkills(pageable);
  }

  @GetMapping("/{id}")
  public String getById(@PathVariable Long id) {
    return skillService.findById(id).toString();
  }

  @DeleteMapping("/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    skillService.deleteById(id);
  }

  @PutMapping("/add")
  public void addVacancy(@RequestBody Skill skill) {
    skillService.save(skill);
  }

  @PatchMapping("/{id}")
  public void updateVacancy(@PathVariable Long id, @RequestBody Skill skill) {
    skillService.save(skill);
  }
}
