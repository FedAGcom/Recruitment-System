package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dto.request.CompanyChangePasswordRequest;
import com.fedag.recruitmentSystem.dto.request.UserChangePasswordRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllCompanies();

    Page<T> getAllCompanies(Pageable pageable);

    boolean activateCompany(String code);

    void changePassword(CompanyChangePasswordRequest company);

    void confirmPasswordChange(Long id, String password);

    void disableById(Long id);
}
