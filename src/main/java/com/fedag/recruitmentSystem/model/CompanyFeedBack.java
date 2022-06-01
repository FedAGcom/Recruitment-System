package com.fedag.recruitmentSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "company_feedback")
public class CompanyFeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "stars")
    private short stars;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinColumn(name = "user_from_id")
    @JsonBackReference
    private User user;

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinColumn(name = "company_to_id")
    @JsonBackReference
    private Company company;
}
