package com.fedag.recruitmentSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService<T> extends GenericServiceInterface<T> {

    List<T> getAllCompanies();

    Page<T> getAllCompanies(Pageable pageable);

}
