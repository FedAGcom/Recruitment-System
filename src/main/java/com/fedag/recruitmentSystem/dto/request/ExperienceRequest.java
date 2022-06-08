package com.fedag.recruitmentSystem.dto.request;

import com.fedag.recruitmentSystem.dto.response.ResumeShortResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceRequest {
    private Long id;

    private String description;

    private LocalDateTime StartDate;

    private LocalDateTime EndDate;

    private ResumeShortResponse resume;
}
