package com.fedag.recruitmentSystem.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Skill;
import com.fedag.recruitmentSystem.repository.SkillRepository;
import com.fedag.recruitmentSystem.service.impl.SkillServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {

  @Mock
  private SkillRepository skillRepository;

  @InjectMocks
  private SkillServiceImpl skillService;

  @Test
  void testGetAllCompanies() {
    skillService.getAllSkills();
    verify(skillRepository).findAll();
  }

  @Test
  void testGetAllCompaniesPageable() {
    Pageable pageable = Mockito.any(Pageable.class);
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

//  @Test
//  @Disabled
//  void testSave() {
//    Skill skill = new Skill();
//    skillService.save(skill);
//    verify(skillRepository).save(skill);
//  }

  @Test
  void testDeleteById() {
    skillService.deleteById(anyLong());
    verify(skillRepository).deleteById(anyLong());
  }
}