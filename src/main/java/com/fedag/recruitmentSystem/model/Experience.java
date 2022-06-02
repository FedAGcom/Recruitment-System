package com.fedag.recruitmentSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "experience")
@Data
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
    private LocalDateTime StartDate;

    @NotBlank
    @Schema(description = "Дата окончания работы")
    @Column(name = "end_date")
    private LocalDateTime EndDate;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonBackReference
    private Resume resume;
}
