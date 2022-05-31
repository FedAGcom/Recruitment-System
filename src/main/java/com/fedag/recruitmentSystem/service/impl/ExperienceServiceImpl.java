package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dao.ExperienceRepository;
import com.fedag.recruitmentSystem.exceptions.NotFoundException;
import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    @Override
    public List<Experience> findAllExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public Page<Experience> findAllExperiences(Pageable pageable) {
        return experienceRepository.findAll(pageable);
    }

    @Override
    public Experience findExperience(Long id) {
        return experienceRepository.findById(id)
          .orElseThrow(()-> new NotFoundException("Experience record with id: " + id + " not found"));
    }

    @Override
    public Experience saveExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    public void deleteExperience(Long id) {
        experienceRepository.findById(id)
           .orElseThrow(()-> new NotFoundException("Experience record with id: " + id + " not found"));
        experienceRepository.deleteById(id);
    }
}
