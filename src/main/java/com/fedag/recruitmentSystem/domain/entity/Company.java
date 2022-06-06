package com.fedag.recruitmentSystem.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fedag.recruitmentSystem.domain.entity.Vacancy;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.model.Message;
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

    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "company")
    @JsonManagedReference
    private List<CompanyFeedBack> companyFeedBackList;

    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "company")
    @JsonBackReference
    private List<Vacancy> vacancyList;

    @OneToMany(mappedBy = "company")
    private List<Message> messageList;

}
