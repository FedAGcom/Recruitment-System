package com.fedag.recruitmentSystem.security;

import com.fedag.recruitmentSystem.email.MailSendlerService;
import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.repository.UserRepository;
import com.fedag.recruitmentSystem.security.jwt.JwtTokenProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private final CompanyRepository companyRepository;

    private final MailSendlerService mailSendler;

    public SecurityService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
                           CompanyRepository companyRepository, MailSendlerService mailSendler) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.companyRepository = companyRepository;
        this.mailSendler = mailSendler;
    }

    public String definitionToken(String email) {
        String token;
        Optional<Company> optionalCompany = companyRepository.findByEmail(email);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            token = jwtTokenProvider.createToken(email, company.getRole().name());
            if(company.getActivationCode() != null) {
                sentMessage(company.getName(), company.getEmail(), company.getActivationCode());
                return "Email not confirm. You are send letter to your email. " +
                        "An email has been re-sent to your email address";
            }
        } else {
            User user = userRepository.findByEmail(email).orElseThrow(
                    () -> new UsernameNotFoundException("User doesn't exists"));
            if(user.getActivationCode() != null) {
                sentMessage(user.getFirstname(), user.getEmail(), user.getActivationCode());
                return "Email not confirm." +
                        " An email has been re-sent to your email address";
            }
            token = jwtTokenProvider.createToken(email, user.getRole().name());
        }
        return token;
    }

    public void sentMessage(String name, String email, String activationCode) {
        String message = String.format("Hello, %s \n" +
                        "Welcome to FedAG. Please, visit next link: " +
                        "http://localhost:8080/api/activate/%s",
                name, activationCode);

        mailSendler.send(email, "Activation code", message);
    }
}
