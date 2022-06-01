package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.repository.VacancyResponseRepository;
import com.fedag.recruitmentSystem.model.VacancyResponse;
import com.fedag.recruitmentSystem.service.VacancyResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VacancyResponseServiceImpl implements VacancyResponseService {

    private final VacancyResponseRepository vacancyResponseRepository;

    @Transactional
    @Override
    public Page<VacancyResponse> getAllVacancyResponse(Pageable pageable) {
        return vacancyResponseRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public VacancyResponse getVacancyResponseById(Long id) {
        return vacancyResponseRepository.getById(id);
    }

    @Transactional
    @Override
    public VacancyResponse addVacancyResponse(VacancyResponse vacancyResponse) {
        return vacancyResponseRepository.save(vacancyResponse);
    }

    @Transactional
    @Override
    public void removeVacancyResponse(Long id) {
        vacancyResponseRepository.deleteById(id);
    }
}
