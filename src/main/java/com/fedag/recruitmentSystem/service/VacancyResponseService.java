package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.VacancyResponse;

import java.util.List;

public interface VacancyResponseService {
    public List<VacancyResponse> getVacancyResponse();

    public VacancyResponse addVacancyResponse(VacancyResponse vacancyResponse);

    public void removeVacancyResponse(Long id);
}
