package com.fedag.recruitmentSystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fedag.recruitmentSystem.enums.MessageStatus;
import com.fedag.recruitmentSystem.enums.FeedbackSentFromEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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