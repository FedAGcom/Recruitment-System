package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.UserChangePasswordRequest;
import com.fedag.recruitmentSystem.dto.request.UserRequest;
import com.fedag.recruitmentSystem.dto.request.UserUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.UserResponse;
import com.fedag.recruitmentSystem.email.MailSendlerService;
import com.fedag.recruitmentSystem.enums.ActiveStatus;
import com.fedag.recruitmentSystem.exception.EntityIsExistsException;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.UserMapper;
import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.repository.UserRepository;
import com.fedag.recruitmentSystem.service.UserService;
import com.fedag.recruitmentSystem.utilites.MainUtilites;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService<UserResponse, UserRequest, UserUpdateRequest> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MailSendlerService mailSendler;

    private final CompanyRepository companyRepository;
    @Value("${host.url}")
    private String hostURL;
    @Value("${server.port}")
    private String portURL;

    @Value("${activation.url}")
    private String activationURL;
    @Value("${change.user.pass.url}")
    private String changePassURL;
    private final PasswordEncoder encoder;

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.modelToDto(userRepository.findAll());
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userMapper.modelToDto(userRepository.findAll(pageable));
    }

    @Override
    public List<UserResponse> getByEntranceExamScore(int score) {
        return userMapper.modelToDto(userRepository.findByEntranceExamScore(score));
    }

    @Override
    public List<UserResponse> getByStars(byte stars) {
        return userMapper.modelToDto(userRepository.findByStars(stars));
    }
  
    @Override
    public List<UserResponse> getByExperience(String max) {
        return userMapper.modelToDto(userRepository.findByExperience(max));
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("User with id: " + id + " not found")
                );
        return userMapper.modelToDto(user);
    }

    @Override
    public UserResponse findByEmail(String email) {
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

        Optional<User> userFromDB = userRepository.findByEmail(user.getEmail());
        if(userFromDB.isPresent()) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "User with this email exists");
        }

        if(companyRepository.findAll().stream().map(Company::getEmail).collect(Collectors.toList()).contains(user.getEmail())){
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Company with this email exist. Please, " +
                    "create new email for new role.");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        String link = String.format("%s:%s%suser/%s", hostURL, portURL, activationURL, user.getActivationCode());
        String button = String.format("<form action=\"%s\"><input type=\"submit\" value=\"activate\" /></form>", link);
        String message = String.format("<h1>Hello, %s</h1><div>Welcome to FedAG!</div><div>Please <a href=\"%s\">activate</a> your account.</div>%s",
                user.getFirstname(),
                link,
                button);

        try {
            mailSendler.sendHtmlEmail(user.getEmail(), "Activation code", message);
        } catch(MessagingException e) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void changePassword(UserChangePasswordRequest userRequest) throws EntityIsExistsException {
        User user = userRepository.findById(userRequest.getId()).orElseThrow(
                () -> new ObjectNotFoundException("User with id: " + userRequest.getId() + " not found")
        );
        if(user.getPassword().equals(userRequest.getPassword())) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Password is the same");
        }

        String link = String.format("%s:%s%s%s/%s", hostURL, portURL, changePassURL, userRequest.getId(), userRequest.getPassword());
        String message = String.format("<div>Request to change password</div><div>Please <a href=\"%s\">confirm</a></div>", link);

        try {
            mailSendler.sendHtmlEmail(user.getEmail(), "Password change", message);
        } catch(MessagingException e) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void confirmPasswordChange(Long id, String password) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("User with id: " + id + " not found")
        );
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void update(UserUpdateRequest element) {
        PasswordEncoder encoder = new BCryptPasswordEncoder(12);
        User user = userMapper.dtoToModel(element);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("User with id: " + id + " not found")
                );
        user.setActiveStatus(ActiveStatus.INACTIVE);
        userRepository.save(user);
    }

    @Override
    public void disableById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("User with id: " + id + " not found")
                );
        user.setRole(MainUtilites.switchRoleToOpposite(user.getRole()));
        userRepository.save(user);
    }

    @Override
    public void activateUser(String code) {
        Optional<User> userOptional = userRepository.findByActivationCode(code);
        if (!userOptional.isPresent()) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Activation is failed");
        }
        User user = userOptional.get();
        user.setActivationCode(null);
        userRepository.save(user);
    }
}