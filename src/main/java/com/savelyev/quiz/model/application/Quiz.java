package com.savelyev.quiz.model.application;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Quiz {

    private Topic topic;

    private List<Question> questions = new ArrayList<>();
    public Quiz(Topic topic) {
        List<Word> words = topic.getWords();
        List<Word> shuffle = new ArrayList<>(words);
        for (Word word : words) {
            Collections.shuffle(shuffle);
            Question question = new Question();
            String wordName = word.getWordName();
            question.setRightAnswer(wordName);
            List<String> collect = shuffle.stream()
                    .filter(e -> !e.getWordName().equals(wordName))
                    .limit(3).map(Word::getWordName)
                    .collect(Collectors.toList());
            collect.add(wordName);
            question.setOptions(collect);
            question.setQuestion(word.getMeaning());
            questions.add(question);
        }
    }
}
