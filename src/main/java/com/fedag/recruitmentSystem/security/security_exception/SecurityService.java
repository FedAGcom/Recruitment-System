package com.fedag.recruitmentSystem.security.security_exception;

import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.repository.UserRepository;
import com.fedag.recruitmentSystem.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private final CompanyRepository companyRepository;

    public SecurityService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.companyRepository = companyRepository;
    }

    public String definitionToken(String email) {
        String token;
        Optional<Company> optionalCompany = companyRepository.findByEmail(email);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            token = jwtTokenProvider.createToken(email, company.getRole().name());
        } else {
            User user = userRepository.findByEmail(email).orElseThrow(
                    () -> new UsernameNotFoundException("User doesn't exists"));
            if(user.getActivationCode() != null) {
                return "Email not confirm";
            }
            token = jwtTokenProvider.createToken(email, user.getRole().name());
        }
        return token;
    }
}
