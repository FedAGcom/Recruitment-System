package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.VacancyResponse;

public interface VacancyResponseService {
    public VacancyResponse getVacancyResponse(Long id);

    public VacancyResponse updateVacancyResponse(VacancyResponse vacancyResponse);

/*    public VacancyResponse addVacancyResponse(VacancyResponse vacancyResponse);

    public void removeVacancyResponse(VacancyResponse vacancyResponse);*/
}
