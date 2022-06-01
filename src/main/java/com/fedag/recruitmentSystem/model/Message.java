package com.fedag.recruitmentSystem.model;

import javax.persistence.*;

import com.fedag.recruitmentSystem.enums.MessageStatus;
import com.fedag.recruitmentSystem.enums.TypeEntityFromSendFeedbackToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "from_entity_message")
    @Enumerated(EnumType.STRING)
    private TypeEntityFromSendFeedbackToUser entityType;

    @Column(name = "date_created")
    private Timestamp dateCreated;

    @Column(name = "enum_messages_is_read")
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}