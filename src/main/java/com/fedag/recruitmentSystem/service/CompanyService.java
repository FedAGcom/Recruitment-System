package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService<T, R> extends AbstractServiceInterface<T, R> {

  List<T> getAllCompanies();

  Page<T> getAllCompanies(Pageable pageable);
}
