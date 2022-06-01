package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dao.VacancyResponseRepository;
import com.fedag.recruitmentSystem.model.VacancyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyResponseServiceImpl implements VacancyResponseService {

    private final VacancyResponseRepository vacancyResponseRepository;

    @Transactional
    @Override
    public List<VacancyResponse> getVacancyResponse() {
        return vacancyResponseRepository.findAll();
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
