package com.fedag.recruitmentSystem.model;


import com.fedag.recruitmentSystem.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Schema(description = "Название компании")
    @Column(name = "company_name")
    private String name;

    @NotBlank
    @Schema(description = "Электронная почта")
    @Column(name = "email")
    private String email;

    @NotBlank
    @Schema(description = "Местонахождение")
    @Column(name = "location")
    private String location;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "password")
    private String password;

    @Column(name = "activation_code")
    private String activationCode;

    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "company")
    private List<CompanyFeedBack> companyFeedBackList;

    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "company")
    private List<Vacancy> vacancyList;

    @OneToMany(mappedBy = "company")
    private List<Message> messageList;

}
