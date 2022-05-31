package com.fedag.recruitmentSystem.model;


import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "entrance_exams")
public class Exam {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "score")
    int score;

//    @OneToOne
//    User user;
}
