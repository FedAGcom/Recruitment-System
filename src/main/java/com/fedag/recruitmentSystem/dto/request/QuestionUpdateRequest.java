package com.fedag.recruitmentSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUpdateRequest {

  private String id;
  private String title;
  private String question;
  private String answer;
  private String correct;
}
