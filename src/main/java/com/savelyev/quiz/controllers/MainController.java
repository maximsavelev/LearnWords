package com.savelyev.quiz.controllers;

import com.savelyev.quiz.repositories.WordRepository;
import com.savelyev.quiz.services.ThemeService;
import com.savelyev.quiz.services.TopicService;
import com.savelyev.quiz.services.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String setPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("topic", topicService.findTopicById(id));
        return "set";
    }


    @GetMapping("/header")
    public String header() {
        return "header";
    }


    @PostMapping("/search")
    public String search(@RequestParam("wordName") String word) {
        System.out.println("word "+word);
        return "redirect:words/" + word;
    }
}
