package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.UserChangePasswordRequest;
import com.fedag.recruitmentSystem.dto.request.UserRequest;
import com.fedag.recruitmentSystem.dto.request.UserUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.UserResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.UserResponseForUser;
import com.fedag.recruitmentSystem.email.MailSendlerService;
import com.fedag.recruitmentSystem.enums.EmailCodeType;
import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.exception.EntityIsExistsException;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.UserMapper;
import com.fedag.recruitmentSystem.model.EmailCode;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.repository.EmailCodeRepository;
import com.fedag.recruitmentSystem.repository.UserRepository;
import com.fedag.recruitmentSystem.service.UserService;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService<UserResponseForAdmin,
        UserRequest, UserUpdateRequest> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MailSendlerService mailSendler;
    private final EmailCodeRepository emailCodeRepository;
    private final CompanyRepository companyRepository;
    @Value("${server.port}") private String portURL;
    private final PasswordEncoder encoder;

    @Override
    public List<UserResponseForAdmin> getAllUsers() {
        return userMapper.modelToDto(userRepository.findAll());
    }

    @Override
    public Page<UserResponseForAdmin> getAllUsers(Pageable pageable) {
        return userMapper.modelToDto(userRepository.findAll(pageable));
    }

    @Override
    public List<UserResponseForAdmin> getByEntranceExamScore(int score) {
        return userMapper.modelToDto(userRepository.findByEntranceExamScore(score));
    }

    @Override
    public List<UserResponseForAdmin> getByStars(byte stars) {
        return userMapper.modelToDto(userRepository.findByStars(stars));
    }

    @Override
    public List<UserResponseForAdmin> getByExperience(String max) {
        return userMapper.modelToDto(userRepository.findByExperience(max));
    }

    @Override
    public UserResponseForAdmin findById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("User with id: " + id + " not found")
                );
        return userMapper.modelToDto(user);
    }

    @Override
    public UserResponseForAdmin findByEmail(String email) {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new ObjectNotFoundException("User with email: " + email + " not found")
                );
        return userMapper.modelToDto(user);
    }

    @Override
    public void save(UserRequest element) throws EntityIsExistsException {
        User user = userMapper.dtoToModel(element);
        EmailCode emailCode = new EmailCode();

        userRepository.findByEmail(user.getEmail()).
                ifPresent(s -> {
                    throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "User with this email exists");
                });

        companyRepository.findByEmail(user.getEmail()).
                ifPresent(s -> {
                    throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Company with this email exists. Please, create new email for new role.");
                });

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(MainUtilites.switchRoleToInactive(user.getRole()));
        userRepository.save(user);

        emailCode.setEmail(user.getEmail());
        emailCode.setCode(UUID.randomUUID().toString());
        emailCode.setType(EmailCodeType.ACTIVATION);
        emailCodeRepository.save(emailCode);

        String link = String.format("%s:%s%suser/%s", UrlConstants.HOST_URL, portURL, UrlConstants.ACTIVATION_URL,
                emailCode.getCode());
        String message = String.format("<h1>Hello, %s</h1><div>Welcome to FedAG!</div>" +
                        "<div>To activate your account, follow the link:</div><a href=%s>%s</a>",
                user.getFirstname(),
                link, link);

        try {
            mailSendler.sendHtmlEmail(user.getEmail(), "Activation code", message);
        } catch (MessagingException e) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void changePassword(UserChangePasswordRequest userRequest) throws EntityIsExistsException {
        User user = userRepository.findById(userRequest.getId()).orElseThrow(
                () -> new ObjectNotFoundException("User with id: " + userRequest.getId() +
                        " not found"));
        if(user.getPassword().equals(userRequest.getPassword())) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Password is the same");
        }
        EmailCode emailCode = new EmailCode();
        emailCode.setEmail(user.getEmail());
        emailCode.setCode(userRequest.getPassword());
        emailCode.setType(EmailCodeType.PASS_CHANGE);
        emailCodeRepository.save(emailCode);
        String link = UrlConstants.HOST_URL + ":" + portURL + UrlConstants.CHANGE_USER_PASS_URL + emailCode.getCode();
        String message = String.format("<div>Request to change password</div><div>Please" +
                " follow the link: <a href=\"%s\">Confirm</a></div>", link);
        try {
            mailSendler.sendHtmlEmail(user.getEmail(), "Password change", message);
        } catch (MessagingException e) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void confirmPasswordChange(String password) {
        EmailCode emailCode = emailCodeRepository.findPassChangeByCode(password)
                .orElseThrow(
                        () -> new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Change pass failed, no code found")
                );
        User user = userRepository.findByEmail(emailCode.getEmail())
                .orElseThrow(
                        () -> new ObjectNotFoundException("User with email: " + emailCode.getEmail() + " not found")
                );
        user.setPassword(password);
        userRepository.save(user);
        emailCodeRepository.delete(emailCode);
    }

    @Override
    public void update(UserUpdateRequest element) {
        User userDB = userRepository.findById(element.getId())
                .orElseThrow(() -> new ObjectNotFoundException("User with id: " + element.getId() + " not found"));
        User user = userMapper.dtoToModel(element);
        user.setPassword(userDB.getPassword()); //user.setPassword(encoder.encode(userDB.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("User with id: " + id + " not found")
                );
        user.setRole(MainUtilites.switchRoleToOpposite(user.getRole()));
        userRepository.save(user);
    }

    @Override
    public void activateUser(String code) {
        EmailCode emailCode = emailCodeRepository.findActivationByCode(code)
                .orElseThrow(
                        () -> new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Activation failed, no activation code found")
                );

        User user = userRepository.findByEmail(emailCode.getEmail())
                .orElseThrow(
                        () -> new ObjectNotFoundException("User with email: " + emailCode.getEmail() + " not found")
                );

        user.setRole(MainUtilites.switchRoleToOpposite(user.getRole()));
        userRepository.save(user);
        emailCodeRepository.delete(emailCode);
    }

    @Override
    public List<UserResponseForUser> getByStarsForUser(byte stars) {
        return userMapper.modelToDtoForUser(userRepository.findByStars(stars));
    }

    @Override
    public List<UserResponseForUser> getByExperienceForUser(String max) {
        return userMapper.modelToDtoForUser(userRepository.findByExperience(max));
    }

    @Override
    public UserResponseForUser findByIdForUser(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("User with id: " + id + " not found")
                );
        return userMapper.modelToDtoForUser(user);
    }

    @Override
    public List<UserResponseForUser> getByEntranceExamScoreForUser(int score) {
        return userMapper.modelToDtoForUser(userRepository.findByEntranceExamScore(score));
    }

    @Override
    public Page<UserResponseForUser> getAllUsersForUser(Pageable pageable) {
        return userMapper.modelToDtoForUser(userRepository.findAll(pageable));
    }
}