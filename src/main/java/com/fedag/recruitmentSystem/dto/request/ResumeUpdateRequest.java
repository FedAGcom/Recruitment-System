package com.fedag.recruitmentSystem.dto.request;

import com.fedag.recruitmentSystem.enums.ResumeStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeUpdateRequest {
    private Long id;

    private String resumeName;

    private ResumeStatus status;

    private LocalDateTime dateCreated;
}
