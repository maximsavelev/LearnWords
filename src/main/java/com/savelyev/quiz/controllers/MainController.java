package com.savelyev.quiz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }

    @GetMapping("/set")
    public String setPage(){
        return "set";
    }

    @GetMapping("/header")
    public String header(){
        return "header";
    }
}
