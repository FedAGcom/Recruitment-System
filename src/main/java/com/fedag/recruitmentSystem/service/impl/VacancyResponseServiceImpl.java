package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.VacancyResponseRequest;
import com.fedag.recruitmentSystem.dto.request.VacancyResponseUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.VacancyResponseResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.VacancyResponseResponseForUser;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.VacancyResponseMapper;
import com.fedag.recruitmentSystem.model.VacancyResponse;
import com.fedag.recruitmentSystem.repository.VacancyResponseRepository;
import com.fedag.recruitmentSystem.service.VacancyResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyResponseServiceImpl implements VacancyResponseService<VacancyResponseResponseForAdmin,
        VacancyResponseRequest, VacancyResponseUpdateRequest> {

    private final VacancyResponseRepository vacancyResponseRepository;
    private final VacancyResponseMapper vacancyResponseMapper;

    @Override
    public List<VacancyResponseResponseForAdmin> getAllVacanciesResponses() {
        return vacancyResponseMapper.modelToDto(vacancyResponseRepository.findAll());
    }

    @Override
    public Page<VacancyResponseResponseForAdmin> getAllVacanciesResponses(Pageable pageable) {
        return vacancyResponseMapper.modelToDto(vacancyResponseRepository.findAll(pageable));
    }

    @Override
    public VacancyResponseResponseForAdmin findById(Long id) {
        VacancyResponse vacancyResponse = vacancyResponseRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("VacancyResponse with id: " +
                                id + " not found")
                );
        return vacancyResponseMapper.modelToDto(vacancyResponse);
    }

    @Override
    public void save(VacancyResponseRequest element) {
        VacancyResponse vacancyResponse = vacancyResponseMapper.dtoToModel(element);
        vacancyResponseRepository.save(vacancyResponse);
    }

    @Override
    public void update(VacancyResponseUpdateRequest element) {
        VacancyResponse vacancyResponse = vacancyResponseMapper.dtoToModel(element);
        vacancyResponseRepository.save(vacancyResponse);
    }

    @Override
    public void deleteById(Long id) {
        vacancyResponseRepository.deleteById(id);
    }

    @Override
    public VacancyResponseResponseForUser findByIdForUser(Long id) {
        VacancyResponse vacancyResponse = vacancyResponseRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("VacancyResponse with id: " +
                                id + " not found")
                );
        return vacancyResponseMapper.modelToDtoForUser(vacancyResponse);
    }

    @Override
    public Page<VacancyResponseResponseForAdmin> getAllVacanciesResponsesByUserId(
            Pageable pageable, Long id) {
        return vacancyResponseMapper.modelToDto(vacancyResponseRepository.findByUserId(pageable, id));
    }

    @Override
    public Page<VacancyResponseResponseForUser> getAllVacanciesResponsesByUserIdForUser(
            Pageable pageable, Long id) {
        return vacancyResponseMapper.modelToDtoForUser(vacancyResponseRepository.findByUserId(pageable, id));
    }
}
