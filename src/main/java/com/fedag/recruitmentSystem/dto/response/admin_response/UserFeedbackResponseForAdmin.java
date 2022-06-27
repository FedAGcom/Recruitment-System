package com.fedag.recruitmentSystem.dto.response.admin_response;

import com.fedag.recruitmentSystem.enums.FeedbackSentFromEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFeedbackResponseForAdmin {

    private Long id;
    private byte stars;
    private String comment;
    private Long entityFromId;
    private FeedbackSentFromEntity entityType;
    private Long userId;
}
