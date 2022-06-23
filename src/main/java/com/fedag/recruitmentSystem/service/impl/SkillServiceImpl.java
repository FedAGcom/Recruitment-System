package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.SkillRequest;
import com.fedag.recruitmentSystem.dto.request.SkillUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.SkillResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.SkillResponseForUser;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.SkillMapper;
import com.fedag.recruitmentSystem.repository.SkillRepository;
import com.fedag.recruitmentSystem.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService<SkillResponseForAdmin,
        SkillRequest, SkillUpdateRequest> {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    @Override
    public List<SkillResponseForAdmin> getAllSkills() {
        return skillRepository
                .findAll()
                .stream()
                .map(skillMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SkillResponseForAdmin> getAllSkills(Pageable pageable) {
        return skillRepository
                .findAll(pageable)
                .map(skillMapper::toDto);
    }

    @Override
    public SkillResponseForAdmin findById(Long id) {
        return skillMapper.toDto
                (skillRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new ObjectNotFoundException("Skill with id: " + id + " not found")
                        ));
    }

    @Override
    public void save(SkillRequest element) {
        skillRepository.save(skillMapper.toEntity(element));
    }

    @Override
    public void update(SkillUpdateRequest element) {
        skillRepository.save(skillMapper.toEntity(element));
    }

    @Override
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public Page<SkillResponseForUser> getAllSkillsForUser(Pageable pageable) {
        return skillRepository
                .findAll(pageable)
                .map(skillMapper::toDtoForUser);
    }
}
