package com.fedag.recruitmentSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "company_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "location")
    private String location;

    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "company",
        fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CompanyFeedBack> companyFeedBackList;

    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "company")
    @JsonBackReference
    private List<Vacancy> vacancyList;

    @OneToMany(
        mappedBy = "company",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<Message> messageList;

}
