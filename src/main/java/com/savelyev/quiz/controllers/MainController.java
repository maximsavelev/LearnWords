package com.savelyev.quiz.controllers;

import com.savelyev.quiz.model.application.Topic;
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

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }

    @GetMapping("/set/{id}")
    public String setPage(@PathVariable("id") Long id, Model model){
        Topic topic = topicService.findTopicById(id);
        model.addAttribute("topic",topic);
        return "set";
    }


    @GetMapping("/header")
    public String header(){
        return "header";
    }
}
