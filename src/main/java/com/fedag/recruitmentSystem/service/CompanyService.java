package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService<T, S> extends AbstractServiceInterface<T, S> {

  List<T> getAllCompanies();

  Page<T> getAllCompanies(Pageable pageable);
}
