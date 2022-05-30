package com.fedag.recruitmentSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "entrance_exams")
public class Exam {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "score")
    int score;

//    @OneToOne
//    User user;
}
