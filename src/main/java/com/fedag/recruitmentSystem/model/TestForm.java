package com.fedag.recruitmentSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestForm {

    private List<String> questions;

    private List<String> answers;

    private List<String> correct;

    private LocalDateTime start;

    private LocalDateTime end;

    private int score;

    private String language;
}
