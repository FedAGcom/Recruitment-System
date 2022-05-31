package com.fedag.recruitmentSystem.model;

import lombok.*;
import javax.persistence.*;
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

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDateTime StartDate;

    @Column(name = "end_date")
    private LocalDateTime EndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "resume_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "experience_resume_id_fkey")
    )
    @Transient
    private Resume resume;
}
