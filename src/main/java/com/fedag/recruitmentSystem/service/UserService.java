package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dto.request.UserChangePasswordRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.UserResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.UserResponseForUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllUsers();

    Page<T> getAllUsers(Pageable pageable);

    void activateUser(String code);

    T findByEmail(String email);

    List<UserResponseForAdmin> getByEntranceExamScore(int score);

    List<UserResponseForAdmin> getByStars(byte stars);

    List<UserResponseForAdmin> getByExperience(String max);

    void changePassword(UserChangePasswordRequest user);

    void confirmPasswordChange(Long id, String password);

    List<UserResponseForUser> getByStarsForUser(byte stars);

    List<UserResponseForUser> getByExperienceForUser(String max);

    UserResponseForUser findByIdForUser(Long id);

    List<UserResponseForUser> getByEntranceExamScoreForUser(int score);

    Page<UserResponseForUser> getAllUsersForUser(Pageable pageable);
}