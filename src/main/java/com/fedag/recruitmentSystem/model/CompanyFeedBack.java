package com.fedag.recruitmentSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "company_to_id")
    private Long companyToId;

    @Column(name = "user_from_id")
    private Long userFromId;

    @Column(name = "stars")
    private short stars;

    @Column(name = "comment")
    private String comment;

    /* @ManyToOne
    private User user;

    @ManyToOne
    private Company company; */
}
