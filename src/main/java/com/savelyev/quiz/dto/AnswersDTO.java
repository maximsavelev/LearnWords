package com.savelyev.quiz.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class AnswersDTO {
    List<String> choices = new ArrayList<>();
}
