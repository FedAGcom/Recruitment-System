package com.fedag.recruitmentSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank
  @Schema(description = "Имя проекта")
  @Column(name = "project_name")
  private String projectName;

  @NotBlank
  @Schema(description = "Описание проекта")
  @Column(name = "description")
  private String description;

  @NotBlank
  @Schema(description = "Требования")
  @Column(name = "requirement")
  private String requirement;

  @NotNull
  @Schema(description = "Дата начала")
  @Column(name = "date_start")
  private Date dateStart;

  @NotNull
  @Schema(description = "Дата окончания")
  @Column(name = "date_end")
  private Date dateEnd;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;

  @ManyToMany
  @JoinTable(
      name = "user_project_link",
      joinColumns = @JoinColumn(name = "project_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;
}
