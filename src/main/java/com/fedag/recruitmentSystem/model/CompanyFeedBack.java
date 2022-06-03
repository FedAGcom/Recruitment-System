package com.fedag.recruitmentSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Schema(description = "Оценка")
    @Column(name = "stars")
    private short stars;

    @NotBlank
    @Schema(description = "Текст отзыва")
    @Column(name = "comment")
    private String comment;

    @NotBlank
    @Schema(description = "Пользователь, который оставил отзыв")
    @ManyToOne
    @JoinColumn(name = "user_from_id")
    @JsonBackReference
    private User user;

    @NotBlank
    @Schema(description = "Компания, на которую оставили отзыв")
    @ManyToOne
    @JoinColumn(name = "company_to_id")
    @JsonBackReference
    private Company company;
}
