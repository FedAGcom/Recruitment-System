package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.service.impl.ExperienceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {
    private final ExperienceServiceImpl experienceService;

    @GetMapping
    public Page<Experience> getAllResumes(@PageableDefault(size = 5) Pageable pageable) {
        return experienceService.findAllExperiences(pageable);
    }

    @GetMapping("/{id}")
    public Experience getById(@PathVariable Long id) {
        return experienceService.findExperience(id);
    }

    @PostMapping
    public Experience addVacancy(@RequestBody Experience experience) {
        return experienceService.saveExperience(experience);
    }

    @PutMapping
    public Experience updateVacancy(@RequestBody Experience experience) {
        return experienceService.saveExperience(experience);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        experienceService.deleteExperience(id);
    }
}
