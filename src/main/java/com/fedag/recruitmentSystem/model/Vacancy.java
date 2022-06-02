package com.fedag.recruitmentSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @NotBlank
  @Schema(description = "Заработная плата")
  @Column(name = "salary")
  private int salary;

  @NotBlank
  @Schema(description = "Требуемый опыт")
  @Column(name = "experience")
  private String experience;

  @NotBlank
  @Schema(description = "Статус")
  @Column(name = "status")
  private String status;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;
}
