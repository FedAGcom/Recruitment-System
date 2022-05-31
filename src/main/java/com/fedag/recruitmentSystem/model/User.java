package com.fedag.recruitmentSystem.model;


import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "first_name")
    String firstname;

    @Column(name = "last_name")
    String lastname;

    @Column(name = "email")
    String email;

    @Column(name = "birthday")
    Date birthday;

//    @OneToOne
//    Exam exam;

//    @OneToMany
//    List<UserFeedback> userFeedbackList;
//
//    @OneToMany
//    List<Resume> resumeList;
//
//    @ManyToMany
//    List<VacancyResponse> vacancyResponseList;
//
//    @OneToMany
//    List<Message> messageList;







}
