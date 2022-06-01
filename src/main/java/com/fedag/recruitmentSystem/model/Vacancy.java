package com.fedag.recruitmentSystem.model;

import javax.persistence.*;

import com.fedag.recruitmentSystem.enums.ResumeStatus;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  private Company company;
}
