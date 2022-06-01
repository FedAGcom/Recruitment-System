package com.fedag.recruitmentSystem.model;

import javax.persistence.*;

import com.fedag.recruitmentSystem.enums.VacancyResponsesStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "vacancy_responses")
public class VacancyResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "enum_vacancy_responses_status_type")
    @Enumerated(EnumType.STRING)
    private VacancyResponsesStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
}
