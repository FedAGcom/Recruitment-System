package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.VacancyResponse;
import com.fedag.recruitmentSystem.service.impl.VacancyResponseServiceImpl;
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
@RequestMapping(value = "/api/vacancies/responses")
public class VacancyResponseController {

  private final VacancyResponseServiceImpl vacancyResponseService;

  @GetMapping
  public Page<VacancyResponse> getAllVacancyResponse(@PageableDefault(size = 5) Pageable pageable) {
    return vacancyResponseService.getAllVacanciesResponses(pageable);
  }

  @GetMapping("/{id}")
  public VacancyResponse getVacancyResponseById(@PathVariable Long id) {
    return vacancyResponseService.findById(id);
  }

  @PostMapping
  public void addVacancyResponse(@RequestBody VacancyResponse vacancyResponse) {
    vacancyResponseService.save(vacancyResponse);
  }

  @PutMapping
  public void updateVacancyResponse(@RequestBody VacancyResponse vacancyResponse) {
    vacancyResponseService.save(vacancyResponse);
  }

  @DeleteMapping("/{id}")
  public void addVacancyResponse(@PathVariable Long id) {
    vacancyResponseService.deleteById(id);
  }
}
