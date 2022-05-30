package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Skill;
import com.fedag.recruitmentSystem.service.impl.SkillServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SkillController {

  private final SkillServiceImpl skillService;

  @GetMapping("/skills")
  public String index() {
    return skillService.index().toString();
  }

  @PostMapping("/skills/delete/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    skillService.deleteById(id);
  }

  @PostMapping("/skills/add")
  public void addVacancy(@ModelAttribute("skill") Skill skill) {
    skillService.save(skill);
  }

  @PostMapping("/skills/update/{id}")
  public void updateVacancy(@ModelAttribute("skill") Skill skill) {
    skillService.save(skill);
  }
}
