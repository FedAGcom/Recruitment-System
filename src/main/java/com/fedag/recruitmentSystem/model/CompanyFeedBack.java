package com.fedag.recruitmentSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @Schema(description = "Оценка")
    @Column(name = "stars")
    private byte stars;

    @NotBlank
    @Schema(description = "Текст отзыва")
    @Column(name = "comment")
    private String comment;

    @Schema(description = "Пользователь, который оставил отзыв")
    @ManyToOne
    @JoinColumn(name = "user_from_id")
    private User user;

    @Schema(description = "Компания, на которую оставили отзыв")
    @ManyToOne
    @JoinColumn(name = "company_to_id")
    private Company company;
}
