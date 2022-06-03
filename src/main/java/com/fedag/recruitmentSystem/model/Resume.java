package com.fedag.recruitmentSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fedag.recruitmentSystem.enums.ResumeStatus;
import lombok.*;
import javax.persistence.*;
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

    @Column(name = "resume_name")
    private String resumeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ResumeStatus status;

    @OneToMany(
            mappedBy = "resume",
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Experience> experiences = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "resume_skill_link",
        joinColumns = @JoinColumn(name = "resume_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id"))
    @JsonBackReference
    private List<Skill> skills;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
