package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExperienceService {
    List<Experience> findAllExperiences();
    Page<Experience> findAllExperiences(Pageable pageable);
    Experience findExperience(Long id);
    Experience saveExperience(Experience experience);
    void deleteExperience(Long id);
}
