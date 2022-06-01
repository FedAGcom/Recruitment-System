package com.fedag.recruitmentSystem.model;

import com.fedag.recruitmentSystem.enums.ResumeStatus;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resume")
@Data
@NoArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "resume_name")
    private String resumeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ResumeStatus status;

    @OneToMany(
            mappedBy = "resume",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    @Transient
    private List<Experience> experiences = new ArrayList<>();

//    @OneToMany
//    private List<Skill> skills = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "user_resume_fk")
    )
    private User user;
}
