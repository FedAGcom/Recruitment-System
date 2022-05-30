package com.fedag.recruitmentSystem.model;

import com.fedag.recruitmentSystem.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resume")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "resume_name")
    private String resumeName;

    @Column(name = "enum_resume_vacancy_status")
    private Status status;

    @OneToMany(
            mappedBy = "resume",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Experience> experiences = new ArrayList<>();

//    @OneToMany
//    private List<Skill> skills = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(
//            name = "user_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(name = "user_resume_fk")
//    )
//    private User user;
}
