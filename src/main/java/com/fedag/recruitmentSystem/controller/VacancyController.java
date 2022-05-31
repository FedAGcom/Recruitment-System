package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/vacancies")
public class VacancyController {

  private final VacancyServiceImpl vacancyService;

  @GetMapping
  public String getAllVacancies(
      @RequestParam(defaultValue = "0") Integer pageNum,
      @RequestParam(defaultValue = "5") Integer pageSize) {
    Pageable paging = PageRequest.of(pageNum, pageSize);
    return vacancyService.getAllVacancies(paging).getContent().toString();
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
