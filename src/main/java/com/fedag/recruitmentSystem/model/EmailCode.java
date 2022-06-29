package com.fedag.recruitmentSystem.model;

import com.fedag.recruitmentSystem.enums.EmailCodeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "email_code")
@IdClass(EmailCodeId.class)
public class EmailCode {
    @NotNull
    @NotBlank
    @Id
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EmailCodeType type;
}
