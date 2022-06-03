package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

  @PostMapping
  public void addVacancy(@RequestBody Vacancy vacancy) {
    vacancyService.save(vacancy);
  }

  @PutMapping
  public void updateVacancy(@RequestBody Vacancy vacancy) {
    vacancyService.save(vacancy);
  }

  @DeleteMapping("/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    vacancyService.deleteById(id);
  }

  @GetMapping("/filter/date")
  public List<Vacancy> findByDateCreated(@RequestParam(defaultValue = "0", required = false) LocalDateTime dateCreated) {
    return vacancyService.findByDateCreated(dateCreated);
  }
}
