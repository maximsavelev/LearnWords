package com.savelyev.quiz.controllers;

import com.savelyev.quiz.model.application.Topic;
import com.savelyev.quiz.services.ThemeService;
import com.savelyev.quiz.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final TopicService topicService;

    private final ThemeService themeService;
    @GetMapping("/main")
    public String mainPage(Model model){
        model.addAttribute("themes", themeService.findAll());
        return "main";
    }

    @GetMapping("/topics/{id}")
    public String setPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("topic",topicService.findTopicById(id));
        return "set";
    }


    @GetMapping("/header")
    public String header(){
        return "header";
    }
}
