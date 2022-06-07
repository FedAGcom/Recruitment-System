package com.fedag.recruitmentSystem.dto.request;

import com.fedag.recruitmentSystem.enums.ResumeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeRequest {
    private Long id;

    private String resumeName;

    private ResumeStatus status;

    private LocalDateTime dateCreated;
}
