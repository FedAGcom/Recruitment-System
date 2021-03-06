package com.fedag.recruitmentSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fedag.recruitmentSystem.enums.VacancyResponsesStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotBlank
    @Schema(description = "Сопроводительное письмо")
    @Column(name = "message")
    private String message;

    @NotNull
    @Schema(description = "Текущий статус")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VacancyResponsesStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;
}
