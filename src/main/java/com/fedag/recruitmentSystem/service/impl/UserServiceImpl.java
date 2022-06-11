package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.UserRequest;
import com.fedag.recruitmentSystem.dto.request.UserUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.UserResponse;
import com.fedag.recruitmentSystem.email.MailSendlerService;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.UserMapper;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.UserRepository;
import com.fedag.recruitmentSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService<UserResponse, UserRequest, UserUpdateRequest> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final MailSendlerService mailSendler;

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

    public List<UserResponse> getByExperience(int max) {
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
    public void save(UserRequest element) {
        PasswordEncoder encoder = new BCryptPasswordEncoder(12);
        User user = userMapper.dtoToModel(element);
        System.out.println(user.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        userRepository.save(user);
    }

    @Override
    public boolean saveUser(UserRequest element) {
        PasswordEncoder encoder = new BCryptPasswordEncoder(12);
        User user = userMapper.dtoToModel(element);
        Optional<User> userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB.isPresent()) {
            return false;
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        String message = String.format("Hello, %s \n" +
                        "Welcome to FedAG. Please, visit next link: " +
                        "http://localhost:8080/api/activate/%s",
                user.getFirstname(),
                user.getActivationCode());

        mailSendler.send(user.getEmail(), "Activation code", message);

        return true;
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
        userRepository.deleteById(id);
    }

    public boolean activateUser(String code) {
        Optional<User> userOptional = userRepository.findByActivationCode(code);
        if(!userOptional.isPresent()) {
            return false;
        }
        User user = userOptional.get();
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }
}