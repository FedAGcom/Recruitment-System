package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dto.request.CompanyRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllCompanies();

    Page<T> getAllCompanies(Pageable pageable);

    boolean saveCompany(CompanyRequest companyRequest);

    boolean activateCompany(String code);
}
