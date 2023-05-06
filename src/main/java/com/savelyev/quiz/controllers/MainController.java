package com.savelyev.quiz.controllers;

import com.savelyev.quiz.dto.AnswersDTO;
import com.savelyev.quiz.model.application.Quiz;
import com.savelyev.quiz.model.application.Topic;
import com.savelyev.quiz.model.application.Word;
import com.savelyev.quiz.services.ThemeService;
import com.savelyev.quiz.services.TopicService;
import com.savelyev.quiz.services.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final TopicService topicService;

    private final ThemeService themeService;
    private final WordService wordService;

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("themes", themeService.findAll());
        return "main";
    }

    @GetMapping("/words/{word}")
    public String word(Model model, @PathVariable String word) {
        model.addAttribute("word", wordService.findWordByWordName(word));
        return "word";
    }

    @GetMapping("/topics/{id}")
    public String topicPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("topic", topicService.findTopicById(id));
        return "set";
    }

    @PostMapping("/topics/{id}")
    public String startQuiz(@PathVariable("id") Long id) {
        return "redirect:/quiz/"+id;
    }


    @GetMapping("/header")
    public String header() {
        return "header";
    }


    @PostMapping("/search")
    public String search(@RequestParam("wordName") String word) {
        System.out.println("word " + word);
        return "redirect:words/" + word;
    }


    @GetMapping("/quiz/{id}")
    public String quiz(@PathVariable("id") Long id, Model model) {
        Topic topicById = topicService.findTopicById(id);
        System.out.println("here are words");
        for (Word word : topicById.getWords()) {
            System.out.println(word);
        }
        Quiz quiz = new Quiz(topicById);
        model.addAttribute("quiz", quiz);
        model.addAttribute("answers", new AnswersDTO());
        return "quiz";
    }

    @PostMapping("/quiz/{id}")
    public String quiz(@ModelAttribute("answers") AnswersDTO answersDTO, @PathVariable Long id) {
        List<Word> words = topicService.findTopicById(id).getWords();
        int counter = 0;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWordName().equals(answersDTO.getChoices().get(i))) {
                counter++;
            }
        }
        System.out.println("Правильных ответов:" + counter + " /" + words.size());
        System.out.println(answersDTO);
        return "redirect:/main";
    }
}
