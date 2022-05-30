package com.fedag.recruitmentSystem.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "experience")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDateTime StartDate;

    @Column(name = "end_date")
    private LocalDateTime EndDate;

    @ManyToOne
    @JoinColumn(
            name = "resume_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "resume_experience_fk")
    )
    private Resume resume;
}
