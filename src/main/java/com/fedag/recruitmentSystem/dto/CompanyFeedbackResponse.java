package com.fedag.recruitmentSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFeedbackResponse {
    private Long id;
    private String stars;
    private String comment;
}
