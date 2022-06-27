package com.fedag.recruitmentSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "education")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Schema(description = "Наименование учебного заведения")
    @Column(name = "name")
    private String name;

    @Schema(description = "Факультет")
    @Column(name = "faculty")
    private String faculty;

    @Schema(description = "Специализация")
    @Column(name = "specialty")
    private String specialty;

    @Schema(description = "Год окончания обучения")
    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
