package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dao.VacancyResponseRepository;
import com.fedag.recruitmentSystem.model.VacancyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VacancyResponseServiceImpl implements VacancyResponseService {

    private final VacancyResponseRepository vacancyResponseRepository;

    @Transactional
    @Override
    public VacancyResponse getVacancyResponse(Long id) {
        return vacancyResponseRepository.getById(id);
    }

    @Transactional
    @Override
    public VacancyResponse updateVacancyResponse(VacancyResponse vacancyResponse) {
        return vacancyResponseRepository.save(vacancyResponse);
    }
}
