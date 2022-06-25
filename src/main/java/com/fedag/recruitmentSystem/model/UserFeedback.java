package com.fedag.recruitmentSystem.model;


import com.fedag.recruitmentSystem.enums.FeedbackSentFromEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_feedback")
public class UserFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Schema(description = "Оценка")
    @Column(name = "stars")
    private byte stars;

    @NotBlank
    @Schema(description = "Комментарий")
    @Column(name = "comment")
    private String comment;

    @NotNull
    @Schema(description = "Сущность, которая оставила отзыв")
    @Column(name = "entity_from_id")
    private Long entityFromId;

    @NotNull
    @Schema(description = "Тип сущности")
    @Column(name = "entity_from_type")
    @Enumerated(EnumType.STRING)
    private FeedbackSentFromEntity entityType;

    @ManyToOne
    @JoinColumn(name = "user_to_id")
    private User user;
}
