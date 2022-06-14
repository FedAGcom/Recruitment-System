package com.fedag.recruitmentSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFeedbackUpdateRequest {
    private Long id;
    private byte stars;
    private String comment;
    private Long companyToId;
    private Long userFromId;
}
