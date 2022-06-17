package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.Company;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

  Optional<Company> findByEmail(String email);

  Optional<Company> findByActivationCode(String code);
}
