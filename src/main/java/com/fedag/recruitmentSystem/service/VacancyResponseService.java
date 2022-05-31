package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.Message;
import com.fedag.recruitmentSystem.model.VacancyResponse;

import java.util.List;

public interface VacancyResponseService {
    public VacancyResponse getVacancyResponse(Long id);

    public VacancyResponse updateVacancyResponse(VacancyResponse vacancyResponse);

/*    public VacancyResponse addVacancyResponse(VacancyResponse vacancyResponse);

    public void removeVacancyResponse(VacancyResponse vacancyResponse);*/
}
