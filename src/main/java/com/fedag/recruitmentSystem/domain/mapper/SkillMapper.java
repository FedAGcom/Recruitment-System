package com.fedag.recruitmentSystem.domain.mapper;

import com.fedag.recruitmentSystem.domain.dto.SkillRequest;
import com.fedag.recruitmentSystem.domain.dto.SkillResponse;
import com.fedag.recruitmentSystem.domain.entity.Skill;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkillMapper {

  private final ModelMapper mapper;

  public SkillResponse toDto(Skill skill) {
    return mapper.map(skill, SkillResponse.class);
  }

  public Skill toEntity(SkillRequest skillRequest) {
    return mapper.map(skillRequest, Skill.class);
  }
}
