package com.savelyev.quiz.services;

import com.savelyev.quiz.model.application.Theme;
import com.savelyev.quiz.repositories.ThemeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ThemeService {
    private final ThemeRepository themeRepository;

    List<Theme> findAll(){
       return themeRepository.findAll();
    }
}
