package com.fedag.recruitmentSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "experience")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank
  @Schema(description = "Описание")
  @Column(name = "description")
  private String description;

  @NotBlank
  @Schema(description = "Дата начала работы")
  @Column(name = "start_date")
  private LocalDateTime startDate;

  @NotBlank
  @Schema(description = "Дата окончания работы")
  @Column(name = "end_date")
  private LocalDateTime endDate;

  @ManyToOne
  @JoinColumn(name = "resume_id")
  private Resume resume;
}
