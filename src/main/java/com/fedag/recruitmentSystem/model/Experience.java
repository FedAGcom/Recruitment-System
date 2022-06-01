package com.fedag.recruitmentSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinColumn(name = "resume_id")
    @JsonBackReference
    private Resume resume;
}
