package com.fedag.recruitmentSystem.model;

import com.fedag.recruitmentSystem.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    @Schema(description = "Должность")
    @Column(name = "resume_name")
    private String resumeName;

    @NotBlank
    @Schema(description = "Статус поиска (Да/Нет)")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

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

//    @ManyToOne
//    @JoinColumn(
//            name = "user_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(name = "user_resume_fk")
//    )
//    private User user;
}
