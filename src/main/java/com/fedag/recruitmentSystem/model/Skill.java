package com.fedag.recruitmentSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "skills")
public class Skill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank
  @Schema(description = "Ключевой навык")
  @Column(name = "skill_name")
  private String name;

  @ManyToMany
  @JoinTable(
      name = "resume_skill_link",
      joinColumns = @JoinColumn(name = "skill_id"),
      inverseJoinColumns = @JoinColumn(name = "resume_id"))
  private List<Resume> resumeList;

  @ManyToMany
  @JoinTable(
      name = "vacancies_skill_link",
      joinColumns = @JoinColumn(name = "skill_id"),
      inverseJoinColumns = @JoinColumn(name = "vacancy_id"))
  private List<Vacancy> vacancyList;

}
