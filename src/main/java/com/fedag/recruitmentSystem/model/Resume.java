package com.fedag.recruitmentSystem.model;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resume")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank
  @Schema(description = "Должность")
  @Column(name = "resume_name")
  private String resumeName;

  @NotBlank
  @Schema(description = "Статус поиска (Да/Нет)")
  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private ActiveStatus status;

  @NotBlank
  @Schema(description = "Дата размещения")
  @Column(name = "date_created")
  private LocalDateTime dateCreated;

  @OneToMany(
      mappedBy = "resume",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.MERGE, CascadeType.REMOVE})
  private List<Experience> experiences = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "resume_skill_link",
      joinColumns = @JoinColumn(name = "resume_id"),
      inverseJoinColumns = @JoinColumn(name = "skill_id"))
  private List<Skill> skills;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
