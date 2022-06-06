package com.fedag.recruitmentSystem.domain.mapper;

import com.fedag.recruitmentSystem.domain.dto.SkillDto;
import com.fedag.recruitmentSystem.domain.entity.Skill;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkillMapper {

  private final ModelMapper mapper;

  public SkillDto toDto(Skill skill) {
    return mapper.map(skill, SkillDto.class);
  }

  public Skill toEntity(SkillDto skillDto) {
    return mapper.map(skillDto, Skill.class);
  }
}
