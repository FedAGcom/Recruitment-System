package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.SkillRequest;
import com.fedag.recruitmentSystem.dto.request.SkillUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.SkillResponse;
import com.fedag.recruitmentSystem.model.Skill;
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

  public Skill toEntity(SkillUpdateRequest skillRequest) {
    return mapper.map(skillRequest, Skill.class);
  }
}
