package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.VacancyResponse;
import com.fedag.recruitmentSystem.service.VacancyResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VacancyResponseController {

    private VacancyResponseService vacancyResponseService;

    @Autowired
    public VacancyResponseController(VacancyResponseService vacancyResponseService) {
        this.vacancyResponseService = vacancyResponseService;
    }

    @GetMapping("/vacancies/response")
    public VacancyResponse getAllVacancyResponse(Long id) {
        return vacancyResponseService.getVacancyResponse(id);
    }

    @PutMapping("vacancies/response/update")
    public VacancyResponse updateVacancyResponse(@RequestBody VacancyResponse vacancyResponse) {
        return vacancyResponseService.updateVacancyResponse(vacancyResponse);
    }
}
