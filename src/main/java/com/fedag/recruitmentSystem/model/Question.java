package com.fedag.recruitmentSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

  private String id;

  private String title;

  private String question;

  private String answer;

  private String correct;

}
