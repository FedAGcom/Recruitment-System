package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.VacancyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VacancyResponseService {

    public Page<VacancyResponse> getAllVacancyResponse(Pageable pageable);

    public VacancyResponse getVacancyResponseById(Long id);

    public VacancyResponse addVacancyResponse(VacancyResponse vacancyResponse);

    public void removeVacancyResponse(Long id);
}
