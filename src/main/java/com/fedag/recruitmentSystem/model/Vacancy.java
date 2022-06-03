package com.fedag.recruitmentSystem.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fedag.recruitmentSystem.enums.ResumeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @Column(name = "header")
  private String header;

  @Column(name = "description")
  private String description;

  @Column(name = "salary")
  private int salary;

  @Column(name = "experience")
  private String experience;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private ResumeStatus status;

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
  @JsonBackReference
  private List<Skill> skillList;
}
