package com.fedag.recruitmentSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
  private String status;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;
}
