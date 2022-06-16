package com.fedag.recruitmentSystem.service.impl;

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
import com.fedag.recruitmentSystem.security.security_exception.ActivationException;
import com.fedag.recruitmentSystem.service.UserService;
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
public class UserServiceImpl implements UserService<UserResponse, UserRequest, UserUpdateRequest> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MailSendlerService mailSendler;

    private final CompanyRepository companyRepository;
    @Value("${activation.url}")
    private String activationURL;

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.modelToDto(userRepository.findAll());
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userMapper.modelToDto(userRepository.findAll(pageable));
    }

    public List<UserResponse> getByEntranceExamScore(int score) {
        return userMapper.modelToDto(userRepository.findByEntranceExamScore(score));
    }

    public List<UserResponse> getByStars(byte stars) {
        return userMapper.modelToDto(userRepository.findByStars(stars));
    }

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
    public void save(UserRequest element) throws EntityIsExistsException {
        PasswordEncoder encoder = new BCryptPasswordEncoder(12);
        User user = userMapper.dtoToModel(element);
        Optional<User> userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB.isPresent()) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "User with this email exists");
        }
        if(companyRepository.findAll().stream().map(Company::getEmail).collect(Collectors.toList()).contains(user.getEmail())){
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST, "Company with this email exist. Please, " +
                    "create new email for new role.");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        String message = String.format("Hello, %s \n" +
                        "Welcome to FedAG. Please, visit next link: " +
                        activationURL + "user/%s",
                user.getFirstname(),
                user.getActivationCode());

        mailSendler.send(user.getEmail(), "Activation code", message);

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
    public boolean activateUser(String code) {
        Optional<User> userOptional = userRepository.findByActivationCode(code);
        if (!userOptional.isPresent()) {
            throw new EntityIsExistsException(HttpStatus.BAD_REQUEST ,"Activation is failed");
        }
        User user = userOptional.get();
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }
}