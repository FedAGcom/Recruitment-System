package com.fedag.recruitmentSystem.dto.response.admin_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFeedbackResponse {
    private Long id;
    private byte stars;
    private String comment;
    private Long companyToId;
    private Long userFromId;
}
