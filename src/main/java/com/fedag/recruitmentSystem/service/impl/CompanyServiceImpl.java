package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.CompanyChangePasswordRequest;
import com.fedag.recruitmentSystem.dto.request.CompanyRequest;
import com.fedag.recruitmentSystem.dto.request.CompanyUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.CompanyResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.CompanyResponseForUser;
import com.fedag.recruitmentSystem.email.MailSendlerService;
import com.fedag.recruitmentSystem.exception.EntityIsExistsException;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.CompanyMapper;
import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.repository.UserRepository;
import com.fedag.recruitmentSystem.service.CompanyService;
import com.fedag.recruitmentSystem.service.utils.MainUtilites;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService<CompanyResponseForAdmin,
        CompanyRequest, CompanyUpdateRequest> {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final MailSendlerService mailSendler;
    private final UserRepository userRepository;

    @Value("${host.url}")
    private String hostURL;
    @Value("${server.port}")
    private String portURL;

    @Value("${activation.url}")
    private String activationURL;
    @Value("${change.company.pass.url}")
    private String changePassURL;
    private final PasswordEncoder encoder;

    @Override
    public List<CompanyResponseForAdmin> getAllCompanies() {
        return companyRepository
                .findAll()
                .stream()
                .map(companyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CompanyResponseForAdmin> getAllCompanies(Pageable pageable) {
        return companyRepository
                .findAll(pageable)
                .map(companyMapper::toDto);
    }

    @Override
    public CompanyResponseForAdmin findById(Long id) {
        return companyMapper.toDto(companyRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Company with id: " + id + " not found")
                ));
    }

    @Override
    public void save(CompanyRequest element) {
        Company company = companyMapper.toEntity(element);
        Optional<Company> companyFromDB = companyRepository.findByEmail(company.getEmail());
        if (companyFromDB.isPresent()) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST,
                    "Company with this email exists");
        }
        if (userRepository.findAll().stream().map(User::getEmail).collect(Collectors.toList())
                .contains(company.getEmail())) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST,
                    "User with this email exists. Please, create new email for new role");
        }
        company.setPassword(encoder.encode(company.getPassword()));
        company.setActivationCode(UUID.randomUUID().toString());
        companyRepository.save(company);

        String link = String.format("%s:%s%scompany/%s", hostURL, portURL, activationURL,
                company.getActivationCode());
        String message = String.format("<h1>Hello, %s</h1><div>Welcome to FedAG!</div>" +
                        "<div>To activate your account, follow the link:</div><a href=%s>%s</a>",
                company.getName(),
                link, link);

        try {
            mailSendler.sendHtmlEmail(company.getEmail(), "Activation code", message);
        } catch (MessagingException e) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void changePassword(CompanyChangePasswordRequest companyRequest) throws EntityIsExistsException {
        Company company = companyRepository.findById(companyRequest.getId()).orElseThrow(
                () -> new ObjectNotFoundException("User with id: " + companyRequest.getId() +
                        " not found")
        );
        if (company.getPassword().equals(companyRequest.getPassword())) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Password is the same");
        }

        String link = hostURL + ":" + portURL + changePassURL + companyRequest.getId() + "/" +
                encoder.encode(companyRequest.getPassword());
        String message = String.format("<div>Request to change password</div>" +
                "<div>Please follow the link: <a href=\"%s\">Confirm</a></div>", link);

        try {
            mailSendler.sendHtmlEmail(company.getEmail(), "Password change", message);
        } catch (MessagingException e) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void confirmPasswordChange(Long id, String password) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("User with id: " + id + " not found")
        );
        company.setPassword(password);
        companyRepository.save(company);
    }

    @Override
    public void update(CompanyUpdateRequest element) {
        Company company = companyMapper.toEntity(element);
        company.setPassword(encoder.encode(company.getPassword()));
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

    @Override
    public void deleteById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Company with id: " + id + " not found")
                );
        company.setRole(MainUtilites.switchRoleToOpposite(company.getRole()));
        companyRepository.save(company);
    }

    @Override
    public Page<CompanyResponseForUser> getAllCompaniesForUser(Pageable pageable) {
        return companyRepository
                .findAll(pageable)
                .map(companyMapper::toDtoForUser);
    }

    @Override
    public CompanyResponseForUser findByIdForUser(Long id) {
        return companyMapper.toDtoForUser(companyRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Company with id: " + id +
                                " not found")
                ));
    }
}
