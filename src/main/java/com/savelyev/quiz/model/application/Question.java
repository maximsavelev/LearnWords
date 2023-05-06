package com.savelyev.quiz.model.application;

import com.savelyev.quiz.model.security.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Question extends BaseEntity {

    private Quiz quiz;
    private String question;
    private String rightAnswer;
    private List<String> options = new ArrayList<>();
    private int choice;
}
