package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.service.impl.VacancyServiceImpl;
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
@RequestMapping(value = "/api/vacancies")
public class VacancyController {

  private final VacancyServiceImpl vacancyService;

  @GetMapping
  public Page<Vacancy> getAllVacancies(@PageableDefault(size = 5) Pageable pageable) {
    return vacancyService.getAllVacancies(pageable);
  }

  @GetMapping("/{id}")
  public String getById(@PathVariable Long id) {
    return vacancyService.findById(id).toString();
  }

  @DeleteMapping("/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    vacancyService.deleteById(id);
  }

  @PutMapping("/add")
  public void addVacancy(@RequestBody Vacancy vacancy) {
    vacancyService.save(vacancy);
  }

  @PatchMapping("/{id}")
  public void updateVacancy(@PathVariable Long id, @RequestBody Vacancy vacancy) {
    vacancyService.save(vacancy);
  }
}
