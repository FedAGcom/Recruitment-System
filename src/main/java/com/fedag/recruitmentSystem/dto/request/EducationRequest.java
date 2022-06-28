package com.fedag.recruitmentSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationRequest {
    private Long id;
    private String name;
    private String faculty;
    private String specialty;
    private int age;
    private Long userId;
}
