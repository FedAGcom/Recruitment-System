package com.fedag.recruitmentSystem.repository.criteria;

import com.fedag.recruitmentSystem.model.Company;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCriteriaRepository {

  List<Company> findByStars(byte stars);
}
