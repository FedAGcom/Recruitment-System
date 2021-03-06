package com.fedag.recruitmentSystem.model;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Schema(description = "Заголовок")
    @Column(name = "header")
    private String header;

    @NotBlank
    @Schema(description = "Описание вакансии")
    @Column(name = "description")
    private String description;

    @NotNull
    @Schema(description = "Заработная плата")
    @Column(name = "salary")
    private int salary;

    @NotBlank
    @Schema(description = "Требуемый опыт")
    @Column(name = "experience")
    private String experience;

    @NotNull
    @Schema(description = "Статус")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

    @NotNull
    @Schema(description = "Дата размещения")
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "vacancies_skill_link",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skillList;
}
