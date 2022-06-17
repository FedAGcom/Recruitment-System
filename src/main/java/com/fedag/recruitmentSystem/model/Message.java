package com.fedag.recruitmentSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fedag.recruitmentSystem.enums.FeedbackSentFromEntity;
import com.fedag.recruitmentSystem.enums.MessageStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "messages")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank
  @Schema(description = "От кого сообщение")
  @Column(name = "from_entity_message")
  @Enumerated(EnumType.STRING)
  private FeedbackSentFromEntity entityType;

  @NotBlank
  @Column(name = "date_created")
  private LocalDateTime dateCreated;

  @NotBlank
  @Schema(description = "Прочитано или нет")
  @Column(name = "is_read")
  @Enumerated(EnumType.STRING)
  private MessageStatus status;

  @Column(name = "message")
  private String message;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;

  @ManyToOne
  @JoinColumn(name = "company_id")
  @JsonBackReference
  private Company company;
}