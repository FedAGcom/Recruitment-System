package com.fedag.recruitmentSystem.model;

import com.fedag.recruitmentSystem.enums.ResumeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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
    private ResumeStatus status;

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
