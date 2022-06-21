package com.fedag.recruitmentSystem.model;


import com.fedag.recruitmentSystem.enums.ActiveStatus;
import com.fedag.recruitmentSystem.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

    @NotBlank
    @Schema(description = "Имя")
    @Column(name = "first_name")
    private String firstname;

    @NotBlank
    @Schema(description = "Фамилия")
    @Column(name = "last_name")
    private String lastname;

    @NotBlank
    @Schema(description = "Email")
    @Column(name = "email")
    private String email;

    @NotNull
    @Schema(description = "Дата рождения")
    @Column(name = "birthday")
    private LocalDateTime birthday;

    @Schema(description = "Роль")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank
    @Schema(description = "Пароль")
    @Column(name = "password")
    private String password;

    @Schema(description = "Код активации")
    @Column(name = "activation_code")
    private String activationCode;

    @Schema(description = "Статус")
    @Column(name = "active_status")
    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    @Column(name = "calendar_id")
    private String calendarId;

    @OneToOne(
            cascade = CascadeType.ALL,
            mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Exam exam;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<UserFeedback> userFeedbackList;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<Resume> resumeList;

    @OneToMany(mappedBy = "user")
    private List<VacancyResponse> vacancyResponseList;

    @OneToMany(mappedBy = "user")
    private List<Message> messageList;

    @ManyToMany
    @JoinTable(
            name = "user_project_link",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;
}

