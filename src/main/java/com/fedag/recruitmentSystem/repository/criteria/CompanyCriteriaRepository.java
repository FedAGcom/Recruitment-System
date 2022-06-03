package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.User;

import java.util.List;

public interface CompanyCriteriaRepository {
    List<Company> findByStars(byte stars);
}
