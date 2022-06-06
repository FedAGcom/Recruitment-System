package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.domain.dto.SkillRequest;
import com.fedag.recruitmentSystem.domain.dto.SkillResponse;
import com.fedag.recruitmentSystem.domain.mapper.SkillMapper;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.repository.SkillRepository;
import com.fedag.recruitmentSystem.service.SkillService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService<SkillResponse, SkillRequest> {

  private final SkillRepository skillRepository;
  private final SkillMapper skillMapper;

  @Override
  public List<SkillResponse> getAllSkills() {
    return skillRepository
        .findAll()
        .stream()
        .map(skillMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public Page<SkillResponse> getAllSkills(Pageable pageable) {
    return skillRepository
        .findAll(pageable)
        .map(skillMapper::toDto);
  }

  @Override
  public SkillResponse findById(Long id) {
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
  public void deleteById(Long id) {
    skillRepository.deleteById(id);
  }
}
