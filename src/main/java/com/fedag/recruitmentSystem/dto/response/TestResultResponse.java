package com.fedag.recruitmentSystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResultResponse {
    private Long id;
    private String language;
    private byte score;
    private Long userId;
}
