package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.service.impl.ExperienceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ExperienceController {
    private final ExperienceServiceImpl experienceService;

    @GetMapping("/experiences/all")
    public List<Experience> getAllResumes() {
        return experienceService.findAllExperiences();
    }

    @GetMapping("/experiences/get/{id}")
    public Experience getById(@PathVariable Long id) {
        return experienceService.findExperience(id);
    }

    @DeleteMapping("/experiences/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        experienceService.deleteExperience(id);
    }

    @PostMapping("/experiences/save")
    public Experience addVacancy(@RequestBody Experience experience) {
        return experienceService.saveExperience(experience);
    }

    @PutMapping("/experiences/save")
    public Experience updateVacancy(@RequestBody Experience experience) {
        return experienceService.saveExperience(experience);
    }
}
