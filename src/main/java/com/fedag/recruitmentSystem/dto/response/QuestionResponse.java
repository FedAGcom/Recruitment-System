package com.fedag.recruitmentSystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {

  private String title;
  private String question;
  private String answer;
  private String correct;
}
