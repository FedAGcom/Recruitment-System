package com.fedag.recruitmentSystem.dto;

import com.fedag.recruitmentSystem.model.User;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data

public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime birthday;

    public static User convertUserDtoToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setBirthday(userDTO.getBirthday());
        return user;
    }

    public static UserDTO convertUserToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthday(user.getBirthday());
        return userDTO;
    }

    public static Page<UserDTO> convertUserPageToUserDTOPage(Page<User> userPage, Pageable pageable){
        List<UserDTO> userDTOList = new ArrayList<>();
        userPage.forEach(userFromPage -> userDTOList.add(convertUserToUserDTO(userFromPage)));
        return new PageImpl<UserDTO>(userDTOList, pageable, userPage.getTotalElements());
    }



}
