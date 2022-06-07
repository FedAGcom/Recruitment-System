package com.fedag.recruitmentSystem.dto.request;

import com.fedag.recruitmentSystem.enums.FeedbackSentFromEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
