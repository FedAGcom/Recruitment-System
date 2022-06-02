package com.fedag.recruitmentSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @NotBlank
    @Schema(description = "Компания, на которую оставили отзыв")
    @Column(name = "company_to_id")
    private Long companyToId;

    @NotBlank
    @Schema(description = "Пользователь, который оставил отзыв")
    @Column(name = "user_from_id")
    private Long userFromId;

    @NotBlank
    @Schema(description = "Оценка")
    @Column(name = "stars")
    private short stars;

    @NotBlank
    @Schema(description = "Текст отзыва")
    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_from_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_to_id")
    private Company company;
}
