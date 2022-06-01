package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.VacancyResponse;
import com.fedag.recruitmentSystem.service.VacancyResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/vacancy/response")
public class VacancyResponseController {

    private final VacancyResponseService vacancyResponseService;

    @GetMapping
    public List<VacancyResponse> getAllVacancyResponse() {
        return vacancyResponseService.getVacancyResponse();
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
