package com.fedag.recruitmentSystem.model;

import jakarta.persistence.*;
import lombok.Data;

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

//    @ManyToMany
//    List<UserFeedback> userFeedbackList;
//
//    @ManyToMany
//    List<CompanyFeedback> companyFeedbackList;
//
//    @OneToMany
//    List<Resume> resumeList;
//
//    @ManyToMany
//    List<VacancyResponse> vacancyResponseList;
//
//    @ManyToMany
//    List<Message> messageList;







}
