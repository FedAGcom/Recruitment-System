package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.ExperienceRequest;
import com.fedag.recruitmentSystem.dto.request.ExperienceUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ExperienceResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.ExperienceResponseForUser;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.ExperienceMapper;
import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.repository.ExperienceRepository;
import com.fedag.recruitmentSystem.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExperienceServiceImpl implements ExperienceService<ExperienceResponseForAdmin,
        ExperienceRequest, ExperienceUpdateRequest> {

    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;

    @Override
    public List<ExperienceResponseForAdmin> getAllExperience() {
        return experienceMapper.modelToDto(experienceRepository.findAll());
    }

    @Override
    public Page<ExperienceResponseForAdmin> getAllExperience(Pageable pageable) {
        return experienceMapper.modelToDto(experienceRepository.findAll(pageable));
    }

    @Override
    public ExperienceResponseForAdmin findById(Long id) {
        Experience experience = experienceRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Experience with id: " + id + " not found")
                );
        return experienceMapper.modelToDto(experience);
    }

    @Override
    public void save(ExperienceRequest element) {
        Experience experience = experienceMapper.dtoToModel(element);
        Optional<Resume> resume = Optional.ofNullable(experience.getResume());
        resume.ifPresent(r -> {
            if (r.getId() != null)
                experience.setResume(r);
        });
        experienceRepository.save(experience);
    }

    @Override
    public void update(ExperienceUpdateRequest element) {
        Experience experience = experienceMapper.dtoToModel(element);
        Optional<Resume> resume = Optional.ofNullable(experience.getResume());
        resume.ifPresent(r -> {
            if (r.getId() != null)
                experience.setResume(r);
        });
        experienceRepository.save(experience);
    }

    @Override
    public void deleteById(Long id) {
        experienceRepository.deleteById(id);
    }

    @Override
    public ExperienceResponseForUser findByIdForUser(Long id) {
        Experience experience = experienceRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Experience with id: " + id + " not found")
                );
        return experienceMapper.modelToDtoForUser(experience);
    }

    @Override
    public Page<ExperienceResponseForAdmin> getAllExperienceByUserId(Pageable pageable, Long id) {
        return experienceMapper.modelToDto(experienceRepository.findByResumeId(pageable, id));
    }

    @Override
    public Page<ExperienceResponseForUser> getAllExperienceByUserIdForUser(Pageable pageable, Long id) {
        return experienceMapper.modelToDtoForUser(experienceRepository.findByResumeId(pageable, id));
    }
}
