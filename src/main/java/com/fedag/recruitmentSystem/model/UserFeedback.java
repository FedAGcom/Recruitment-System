package com.fedag.recruitmentSystem.model;


import com.fedag.recruitmentSystem.enums.FeedbackSentFromEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @NotBlank
  @Schema(description = "Оценка")
  @Column(name = "stars")
  private byte stars;

  @NotBlank
  @Schema(description = "Комментарий")
  @Column(name = "comment")
  private String comment;

  @NotBlank
  @Schema(description = "Сущность, которая оставила отзыв")
  @Column(name = "entity_from_id")
  private Long entityFromId;

  @NotBlank
  @Schema(description = "Тип сущности")
  @Column(name = "entity_from_type")
  @Enumerated(EnumType.STRING)
  private FeedbackSentFromEntity entityType;

  @ManyToOne
  @JoinColumn(name = "user_to_id")
  private User user;
}
