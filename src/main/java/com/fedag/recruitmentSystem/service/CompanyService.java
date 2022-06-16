package com.fedag.recruitmentSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllCompanies();

    Page<T> getAllCompanies(Pageable pageable);

    void activateCompany(String code);
}
