package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.CompanyRequest;
import com.fedag.recruitmentSystem.dto.request.CompanyUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.CompanyResponse;
import com.fedag.recruitmentSystem.email.MailSendlerService;
import com.fedag.recruitmentSystem.enums.ActiveStatus;
import com.fedag.recruitmentSystem.exception.EntityIsExistsException;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.CompanyMapper;
import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService<CompanyResponse, CompanyRequest, CompanyUpdateRequest> {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final MailSendlerService mailSendler;
    @Value("${activation.url}")
    private String activationURL;

    @Override
    public List<CompanyResponse> getAllCompanies() {
        return companyRepository
                .findAll()
                .stream()
                .map(companyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CompanyResponse> getAllCompanies(Pageable pageable) {
        return companyRepository
                .findAll(pageable)
                .map(companyMapper::toDto);
    }

    @Override
    public CompanyResponse findById(Long id) {
        return companyMapper.toDto(companyRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Company with id: " + id + " not found")
                ));
    }

    @Override
    public void save(CompanyRequest element) throws EntityIsExistsException {
        PasswordEncoder encoder = new BCryptPasswordEncoder(12);
        Company company = companyMapper.toEntity(element);
        Optional<Company> companyFromDB = companyRepository.findByEmail(company.getEmail());
        if (companyFromDB.isPresent()) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Company with this email exists");
        }
        company.setPassword(encoder.encode(company.getPassword()));
        company.setActivationCode(UUID.randomUUID().toString());
        companyRepository.save(company);

        String message = String.format("Hello, %s \n" +
                        "Welcome to FedAG. Please, visit next link: " +
                        activationURL + "company/%s",
                company.getName(),
                company.getActivationCode());

        mailSendler.send(company.getEmail(), "Activation code", message);
    }

    @Override
    public void update(CompanyUpdateRequest element) {
        PasswordEncoder encoder = new BCryptPasswordEncoder(12);
        Company company = companyMapper.toEntity(element);
        company.setPassword(encoder.encode(company.getPassword()));
        companyRepository.save(company);
    }

    @Override
    public void deleteById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Company with id: " + id + " not found")
                );
        company.setActiveStatus(ActiveStatus.INACTIVE);
        companyRepository.save(company);
    }

    @Override
    public void activateCompany(String code) {
        Optional<Company> companyOptional = companyRepository.findByActivationCode(code);
        if (!companyOptional.isPresent()) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Activation is failed");
        }
        Company company = companyOptional.get();
        company.setActivationCode(null);
        companyRepository.save(company);
    }
}
