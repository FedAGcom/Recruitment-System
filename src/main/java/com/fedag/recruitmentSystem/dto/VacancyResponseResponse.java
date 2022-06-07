package com.fedag.recruitmentSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyResponseResponse {
    private Long id;
    private String message;
    private String status;
}