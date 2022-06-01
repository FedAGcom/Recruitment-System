package com.fedag.recruitmentSystem.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

  @Column(name = "skill_name")
  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "resume_skill_link"
          ,joinColumns = @JoinColumn(name = "skill_id")
          ,inverseJoinColumns = @JoinColumn(name = "resume_id"))
  @JsonManagedReference
  private List<Resume> resumeList;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "vacancies_skill_link"
          ,joinColumns = @JoinColumn(name = "skill_id")
          ,inverseJoinColumns = @JoinColumn(name = "vacancy_id"))
  @JsonManagedReference
  private List<Vacancy> vacancyList;

}
