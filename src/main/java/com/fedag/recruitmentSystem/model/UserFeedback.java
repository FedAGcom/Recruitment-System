package com.fedag.recruitmentSystem.model;


import com.fedag.recruitmentSystem.enums.TypeEntityFromSendFeedbackToUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "stars")
    private byte stars;

    @Column(name = "comment")
    private String comment;

    @Column(name = "entity_from_id")
    private Long entityFromId;

    @Column(name = "entity_from_type")
    @Enumerated(EnumType.STRING)
    private TypeEntityFromSendFeedbackToUser entityType;

    @ManyToOne
    @JoinColumn(name = "user_to_id")
    private User user;
}
