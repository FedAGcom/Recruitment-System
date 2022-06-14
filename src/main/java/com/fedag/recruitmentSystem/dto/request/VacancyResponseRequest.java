package com.fedag.recruitmentSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyResponseRequest {
    private Long id;
    private String message;
    private String status;
    private Long vacancyId;
    private Long userId;
}
