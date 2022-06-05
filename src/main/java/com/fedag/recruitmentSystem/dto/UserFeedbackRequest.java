package com.fedag.recruitmentSystem.dto;

import com.fedag.recruitmentSystem.enums.FeedbackSentFromEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFeedbackRequest {

    private Long id;
    private byte stars;
    private String comment;
    private Long entityFromId;
    private FeedbackSentFromEntity entityType;
    private Long userId;
}
