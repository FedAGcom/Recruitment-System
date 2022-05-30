package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.Experience;
import java.util.List;

public interface ExperienceService {
    List<Experience> findAllExperiences();
    Experience findExperience(Long id);
    Experience saveExperience(Experience experience);
    void deleteExperience(Long id);
}
