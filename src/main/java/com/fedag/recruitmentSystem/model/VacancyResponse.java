package com.fedag.recruitmentSystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fedag.recruitmentSystem.enums.VacancyResponsesStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "vacancy_response")
public class VacancyResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "vacancy_id")
    private Long vacancyId;

    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Schema(description = "Сопроводительное письмо")
    @Column(name = "message")
    private String message;

    @NotBlank
    @Schema(description = "Текущий статус")
    @Column(name = "enum_vacancy_responses_status")
    @Enumerated(EnumType.STRING)
    private VacancyResponsesStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
}
