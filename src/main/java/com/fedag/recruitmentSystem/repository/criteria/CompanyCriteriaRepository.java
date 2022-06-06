package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyCriteriaRepository {
    List<Company> findByStars(byte stars);
}
