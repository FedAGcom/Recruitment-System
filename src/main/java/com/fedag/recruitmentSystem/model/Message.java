package com.fedag.recruitmentSystem.model;

import jakarta.persistence.*;
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
    private String fromEntityMessage;

    @Column(name = "data_created")
    private Timestamp dataCreated;

    @Column(name = "is_read")
    private String isRead;

    @Column(name = "message")
    private String message;

    /* @ManyToOne
    private User user;

    @ManyToOne
    private Company company; */
}
