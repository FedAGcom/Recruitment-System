package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.service.impl.VacancyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VacancyController {

  private final VacancyServiceImpl vacancyService;

  @GetMapping("/vacancies")
  public String index() {
    return vacancyService.index().toString();
  }

  @GetMapping("/vacancies/{id}")
  public String getById(@PathVariable Long id) {
    return vacancyService.findById(id).toString();
  }

  @PostMapping("/vacancies/delete/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    vacancyService.deleteById(id);
  }

  @PostMapping("/vacancies/add")
  public void addVacancy(@ModelAttribute("vacancy") Vacancy vacancy) {
    vacancyService.save(vacancy);
  }

  @PostMapping("/vacancies/update/{id}")
  public void updateVacancy(@ModelAttribute("vacancy") Vacancy vacancy) {
    vacancyService.save(vacancy);
  }
}
