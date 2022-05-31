package com.fedag.recruitmentSystem.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private Date birthday;

//    @OneToOne(cascade = CascadeType.ALL,
//    mappedBy = "user")
//    private Exam exam;

//    @OneToMany(cascade = CascadeType.ALL,
//    mappedBy = "user")
//    private List<UserFeedback> userFeedbackList;

//    @OneToMany(cascade = CascadeType.ALL,
//    mappedBy = "user")
//    private List<Resume> resumeList;
//
//    @ManyToMany(mappedBy = "user")
//    @JoinTable(name = "vacancy_responses",
//    joinColumns = @JoinColumn(name = "user_id"),
//    inverseJoinColumns = @JoinColumn(name = "vacancy_id"))
//    private List<VacancyResponse> vacancyResponseList;
//
//    @OneToMany(mappedBy = "user")
//    private List<Message> messageList;
}
