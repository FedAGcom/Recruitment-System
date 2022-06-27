package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dto.response.user_response.SkillResponseForUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SkillService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllSkills();

    Page<T> getAllSkills(Pageable pageable);

    Page<SkillResponseForUser> getAllSkillsForUser(Pageable pageable);
}
