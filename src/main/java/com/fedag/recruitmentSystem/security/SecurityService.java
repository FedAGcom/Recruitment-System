package com.fedag.recruitmentSystem.security;

import com.fedag.recruitmentSystem.email.MailSendlerService;
import com.fedag.recruitmentSystem.enums.EmailCodeType;
import com.fedag.recruitmentSystem.enums.Role;
import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.exception.EntityIsExistsException;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.EmailCode;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.repository.EmailCodeRepository;
import com.fedag.recruitmentSystem.repository.UserRepository;
import com.fedag.recruitmentSystem.security.jwt.JwtTokenProvider;
import com.fedag.recruitmentSystem.security.security_exception.ActivationException;
import com.fedag.recruitmentSystem.service.utils.MainUtilites;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.mail.MessagingException;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityService {

    @Value("${server.port}") private String portURL;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final EmailCodeRepository emailCodeRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailSendlerService mailSendler;

    public boolean isUserInActiveState(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return false;
        }
        return !user.getRole().equals(Role.ADMIN_INACTIVE)
                && !user.getRole().equals(Role.USER_INACTIVE);
    }

    public boolean isCompanyInActiveState(String email) {
        Company company = companyRepository.findByEmail(email).orElse(null);
        if (company == null) {
            return false;
        }
        return !company.getRole().equals(Role.ADMIN_INACTIVE)
                && !company.getRole().equals(Role.USER_INACTIVE);
    }

    public void checkActivation(String name, String entity, String email) {
        emailCodeRepository.findActivationByEmail(email)
                .ifPresent(s -> {
                    sentMessage(name, email, s.getCode(), entity);
                    throw new ActivationException("Email not confirm. " +
                            "A letter has been re-sent to your email address.");
                });
    }

    public String definitionToken(String email) {
        final String[] token = new String[1];
        companyRepository.findByEmail(email).ifPresentOrElse(
          c -> {
            token[0] = jwtTokenProvider.createToken(email, c.getRole().name());
            checkActivation(c.getName(), "company", c.getEmail());
          },
          () -> {
            User user = userRepository.findByEmail(email).orElseThrow(
                      () -> new UsernameNotFoundException("User doesn't exists"));
            checkActivation(user.getFirstname(), "user", user.getEmail());
            token[0] = jwtTokenProvider.createToken(email, user.getRole().name());
          });
        return token[0];
    }

    public void sentMessage(String name, String email, String activationCode, String entity) {
        String link = String.format("%s:%s%s%s/%s", UrlConstants.HOST_URL, portURL, UrlConstants.ACTIVATION_URL, entity, activationCode);
        String message = String.format("<h1>Hello, %s</h1> \n" +
                        "<div>Welcome to FedAG!</div>" +
                        "<div>To activate your account, follow the link:</div><a href=%s>%s</a>",
                name, link, link);
        try {
            mailSendler.sendHtmlEmail(email, "Activation code", message);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Activation is failed");
        }
    }

    public ResponseEntity<?> responseToReactivateAccount(String email) {
        EmailCode emailCode = new EmailCode();
        emailCode.setEmail(email);
        emailCode.setCode(UUID.randomUUID().toString());
        emailCode.setType(EmailCodeType.REACTIVATION);
        emailCodeRepository.save(emailCode);

        String link = String.format("%s:%s/api/activate/restore/%s", UrlConstants.HOST_URL, portURL, emailCode.getCode());
        String message = String.format("<div>Your account is disabled.\n" +
                "Please follow the link to restore it:</div>" +
                "<a href=%s>%s</a>", link, link);
        try {
            mailSendler.sendHtmlEmail(email, "Restore account", message);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restore is failed");
        }
        return new ResponseEntity<>("Account is disabled. Check your email to enable it.",
                HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<?> reactivateAccount(String code) {
        ResponseEntity<?>[] responseEntity = new ResponseEntity<?>[1];
        EmailCode emailCode = emailCodeRepository.findRestoreByCode(code)
                .orElseThrow(
                        () -> new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Restore account failed, no code found")
                );
        companyRepository.findByEmail(emailCode.getEmail()).ifPresentOrElse(
                company -> {
                    company.setRole(MainUtilites.switchRoleToOpposite(company.getRole()));
                    companyRepository.save(company);
                    emailCodeRepository.delete(emailCode);
                    responseEntity[0] = new ResponseEntity<>("Company set to active state successfully.", HttpStatus.OK);
                },
                () -> {
                    User user = userRepository.findByEmail(emailCode.getEmail())
                       .orElseThrow(() -> new ObjectNotFoundException("Restore account failed, no user or company with email: " + emailCode.getEmail() + " found"));
                    user.setRole(MainUtilites.switchRoleToOpposite(user.getRole()));
                    userRepository.save(user);
                    emailCodeRepository.delete(emailCode);
                    responseEntity[0] = new ResponseEntity<>("User set to active state successfully.", HttpStatus.OK);
                });
        return responseEntity[0];
    }

    private String generateCode() {
        Random rand = new Random();
        return String.format("%04d", rand.nextInt(10000));
    }

    public ResponseEntity<?> responseToLoginCode(String email) {
        emailCodeRepository.findLoginCodeByEmail(email)
           .ifPresent(s -> {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login code already sent");
           });
        EmailCode emailCode = new EmailCode();
        emailCode.setEmail(email);
        emailCode.setCode(generateCode());
        emailCode.setType(EmailCodeType.DIGITS_CODE);
        emailCodeRepository.save(emailCode);

        String message = String.format("<div>Your code to login is %s</div>", emailCode.getCode());
        try {
            mailSendler.sendHtmlEmail(email, "Login code", message);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Send login code failed");
        }
        return new ResponseEntity<>("Auth code sent. Please check your email.",
                HttpStatus.OK);
    }
}
