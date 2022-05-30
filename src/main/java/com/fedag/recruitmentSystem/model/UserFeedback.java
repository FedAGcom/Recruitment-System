package com.fedag.recruitmentSystem.model;

import com.fedag.recruitmentSystem.enums.EntityFromType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_feedback")
public class UserFeedback {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "stars")
    byte stars;

    @Column(name = "comment")
    String comment;

    @Column(name = "entity_from_id")
    Long entityFromId;

    @Column(name = "entity_from_type")
    @Enumerated(EnumType.STRING)
    EntityFromType entityType;

//    @OneToOne
//    User user;




}
