package com.fedag.recruitmentSystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private Long id;
    private String entityType;
    private LocalDateTime dateCreated;
    private String status;
    private String message;
}
