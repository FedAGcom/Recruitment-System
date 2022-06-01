package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.VacancyResponse;
import com.fedag.recruitmentSystem.service.VacancyResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/vacancy/response")
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

    @PostMapping("/add")
    public VacancyResponse addVacancyResponse(@RequestBody VacancyResponse vacancyResponse) {
        return vacancyResponseService.addVacancyResponse(vacancyResponse);
    }

    @DeleteMapping("/{id}")
    public void addVacancyResponse(@PathVariable Long id) {
        vacancyResponseService.removeVacancyResponse(id);
    }

}
