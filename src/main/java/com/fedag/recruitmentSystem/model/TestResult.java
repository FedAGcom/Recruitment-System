package com.fedag.recruitmentSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "entrance_exams")
@AllArgsConstructor
@NoArgsConstructor
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Schema(description = "Наименование языка программирования")
    @Column(name = "language")
    private String language;

    @Schema(description = "Оценка за тест")
    @Column(name = "score")
    private byte score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
