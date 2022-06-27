package com.fedag.recruitmentSystem.dto.response;

import com.fedag.recruitmentSystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponse {
    private Long id;
    private String name;
    private String faculty;
    private String specialty;
    private String age;
    private User user;
}
