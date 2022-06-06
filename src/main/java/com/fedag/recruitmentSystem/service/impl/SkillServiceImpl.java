package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.domain.dto.SkillDto;
import com.fedag.recruitmentSystem.domain.mapper.SkillMapper;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.domain.entity.Skill;
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
public class SkillServiceImpl implements SkillService<SkillDto> {

  private final SkillRepository skillRepository;
  private final SkillMapper skillMapper;

  @Override
  public List<SkillDto> getAllSkills() {
    return skillRepository
        .findAll()
        .stream()
        .map(skillMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public Page<SkillDto> getAllSkills(Pageable pageable) {
    return skillRepository
        .findAll(pageable)
        .map(skillMapper::toDto);
  }

  @Override
  public SkillDto findById(Long id) {
    return skillMapper.toDto
        (skillRepository
            .findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException("Skill with id: " + id + " not found")
            ));
  }

  @Override
  public void save(SkillDto element) {
    skillRepository.save(skillMapper.toEntity(element));
  }

  @Override
  public void deleteById(Long id) {
    skillRepository.deleteById(id);
  }
}
