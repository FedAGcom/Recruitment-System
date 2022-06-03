package com.fedag.recruitmentSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
  @JsonManagedReference
  @JsonIgnore
  private List<Resume> resumeList;

  @ManyToMany
  @JoinTable(
      name = "vacancies_skill_link",
      joinColumns = @JoinColumn(name = "skill_id"),
      inverseJoinColumns = @JoinColumn(name = "vacancy_id"))
  @JsonManagedReference
  @JsonIgnore
  private List<Vacancy> vacancyList;

}
