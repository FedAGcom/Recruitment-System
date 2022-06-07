package com.fedag.recruitmentSystem.dto.response;

import com.fedag.recruitmentSystem.dto.request.ResumeRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceResponse {
    private Long id;

    private String description;

    private LocalDateTime StartDate;

    private LocalDateTime EndDate;

    private ResumeRequest resume;
}
