package com.savelyev.quiz.services.security;

import org.springframework.stereotype.Service;

import static com.savelyev.quiz.configs.Translator.toLocale;


@Service
public class TranslatorService {
    public  String getTranslation(String message ) {
        return toLocale(message);
    }

}
