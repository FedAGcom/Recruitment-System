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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "resume_skill_link",
        joinColumns = @JoinColumn(name = "resume_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id"))
    @JsonBackReference
    private List<Skill> skills;

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
