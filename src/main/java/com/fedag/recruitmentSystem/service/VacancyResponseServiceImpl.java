package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dao.VacancyResponseDao;
import com.fedag.recruitmentSystem.model.VacancyResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VacancyResponseServiceImpl implements VacancyResponseService {

    private VacancyResponseDao vacancyResponseDao;

    public VacancyResponseServiceImpl(VacancyResponseDao vacancyResponseDao) {
        this.vacancyResponseDao = vacancyResponseDao;
    }

    @Transactional
    @Override
    public VacancyResponse getVacancyResponse(Long id) {
        return vacancyResponseDao.getReferenceById(id);
    }

    @Transactional
    @Override
    public VacancyResponse updateVacancyResponse(VacancyResponse vacancyResponse) {
        return vacancyResponseDao.save(vacancyResponse);
    }
}
