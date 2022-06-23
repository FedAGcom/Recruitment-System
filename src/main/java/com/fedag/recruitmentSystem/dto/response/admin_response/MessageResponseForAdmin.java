package com.fedag.recruitmentSystem.dto.response.admin_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseForAdmin {
    private Long id;
    private String entityType;
    private LocalDateTime dateCreated;
    private String status;
    private String message;
    private Long userId;
    private Long companyId;
}
