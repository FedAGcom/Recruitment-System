package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dao.ExperienceRepository;
import com.fedag.recruitmentSystem.exceptions.NotFoundException;
import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.service.ExperienceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    @Override
    public List<Experience> findAllExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public Experience findExperience(Long id) {
        return experienceRepository.findById(id)
          .orElseThrow(()-> new NotFoundException("Experience record with id: " + id + " not available"));
    }

    @Override
    public Experience saveExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    public void deleteExperience(Long id) {
        experienceRepository.deleteById(id);
    }
}
