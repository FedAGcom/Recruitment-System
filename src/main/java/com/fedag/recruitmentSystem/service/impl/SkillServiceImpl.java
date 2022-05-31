package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Skill;
import com.fedag.recruitmentSystem.repository.SkillRepository;
import com.fedag.recruitmentSystem.service.SkillService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService<Skill> {

  private final SkillRepository skillRepository;

  @Override
  public List<Skill> getAllSkills() {
    return skillRepository.findAll();
  }

  @Override
  public Page<Skill> getAllSkills(Pageable pageable) {
    return skillRepository.findAll(pageable);
  }

  @Override
  public Skill findById(Long id) {
    return skillRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Skill with id: " + id + " not found")
        );
  }

  @Override
  public void save(Skill element) {
    skillRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    skillRepository.deleteById(id);
  }
}
