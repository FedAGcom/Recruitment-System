package com.fedag.recruitmentSystem.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.fedag.recruitmentSystem.dto.request.SkillRequest;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.SkillMapper;
import com.fedag.recruitmentSystem.model.Skill;
import com.fedag.recruitmentSystem.repository.SkillRepository;
import com.fedag.recruitmentSystem.service.impl.SkillServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {

  @Mock
  private SkillRepository skillRepository;

  @Mock
  private SkillMapper skillMapper;

  @InjectMocks
  private SkillServiceImpl skillService;

  @Test
  void testGetAllCompanies() {
    skillService.getAllSkills();
    verify(skillRepository).findAll();
  }

  @Test
  void testGetAllCompaniesPageable() {
    Pageable pageable = PageRequest.of(0, 5);
    when(skillRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(new Skill())));
    skillService.getAllSkills(pageable);
    verify(skillRepository).findAll(pageable);
  }

  @Test
  void testFindById() {
    when(skillRepository.findById(anyLong())).thenReturn(Optional.of(new Skill()));
    skillService.findById(anyLong());
    verify(skillRepository).findById(anyLong());
  }

  @Test
  void testExpectedExceptionFindById() {
    assertThrows(ObjectNotFoundException.class, () -> skillService.findById(anyLong()));
  }

  @Test
  void testSave() {
    SkillRequest skill = new SkillRequest();
    skillService.save(skill);
    verify(skillRepository).save(skillMapper.toEntity(skill));
  }

  @Test
  void testDeleteById() {
    skillService.deleteById(anyLong());
    verify(skillRepository).deleteById(anyLong());
  }
}