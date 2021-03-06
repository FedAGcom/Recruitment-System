package com.fedag.recruitmentSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageUpdateRequest {
    private Long id;
    private String entityType;
    private LocalDateTime dateCreated;
    private String status;
    private String message;
    private Long userId;
    private Long companyId;

}
