package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.service.impl.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ResumeController {
    private final ResumeServiceImpl resumeService;

    @GetMapping("/resumes/all")
    public List<Resume> getAllResumes() {
        return resumeService.findAllResumes();
    }

    @GetMapping("/resumes/get/{id}")
    public Resume getById(@PathVariable Long id) {
        return resumeService.findResume(id);
    }

    @DeleteMapping("/resumes/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        resumeService.deleteResume(id);
    }

    @PostMapping("/resumes/save")
    public Resume addVacancy(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }

    @PutMapping("/resumes/save")
    public Resume updateVacancy(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }
}
