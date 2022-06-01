package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.model.VacancyResponse;
import com.fedag.recruitmentSystem.service.VacancyResponseService;
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

  private final VacancyResponseService vacancyResponseService;

  @GetMapping
  public Page<VacancyResponse> getAllVacancyResponse(@PageableDefault(size = 5) Pageable pageable) {
    return vacancyResponseService.getAllVacancyResponse(pageable);
  }

  @GetMapping("/{id}")
  public VacancyResponse getVacancyResponseById(@PathVariable Long id) {
    return vacancyResponseService.getVacancyResponseById(id);
  }

  @PostMapping
  public VacancyResponse addVacancyResponse(@RequestBody VacancyResponse vacancyResponse) {
    return vacancyResponseService.addVacancyResponse(vacancyResponse);
  }

  @PutMapping
  public VacancyResponse updateVacancyResponse(@RequestBody VacancyResponse vacancyResponse) {
    return vacancyResponseService.addVacancyResponse(vacancyResponse);
  }

  @DeleteMapping("/{id}")
  public void addVacancyResponse(@PathVariable Long id) {
    vacancyResponseService.removeVacancyResponse(id);
  }
}
