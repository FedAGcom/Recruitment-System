package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.Skill;
import com.fedag.recruitmentSystem.repository.SkillRepository;
import com.fedag.recruitmentSystem.service.SkillService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SkillServiceImpl implements SkillService<Skill> {

  private final SkillRepository skillRepository;

  @Override
  public Skill findById(Long id) {
    Optional<Skill> skill = skillRepository.findById(id);
    if (skill.isEmpty()) {
      throw new IllegalArgumentException("Skill is null");
    }
    return skill.get();
  }

  @Override
  public List<Skill> index() {
    return skillRepository.findAll();
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
