package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.service.impl.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    private final ResumeServiceImpl resumeService;

    @GetMapping
    public Page<Resume> getAllResumes(@PageableDefault(size = 5) Pageable pageable) {
        return resumeService.findAllResumes(pageable);
    }

    @GetMapping("/{id}")
    public Resume getById(@PathVariable Long id) {
        return resumeService.findResume(id);
    }

    @PostMapping
    public Resume addVacancy(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }

    @PutMapping
    public Resume updateVacancy(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        resumeService.deleteResume(id);
    }
}
