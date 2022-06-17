package com.fedag.recruitmentSystem.model;


import com.fedag.recruitmentSystem.enums.ActiveStatus;
import com.fedag.recruitmentSystem.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


  @Schema(description = "Роль")
  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role;

  @NotBlank
  @Schema(description = "Пароль")
  @Column(name = "password")
  private String password;

  @Schema(description = "Идентификатор календаря")
  @Column(name = "calendar_id")
  private String calendarId;

  @Schema(description = "Код активации")
  @Column(name = "activation_code")
  private String activationCode;

  @Schema(description = "Статус")
  @Column(name = "active_status")
  @Enumerated(EnumType.STRING)
  private ActiveStatus activeStatus;

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
