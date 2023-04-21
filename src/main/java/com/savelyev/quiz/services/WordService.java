package com.savelyev.quiz.services;

import com.savelyev.quiz.exception.EntityNotFound;
import com.savelyev.quiz.model.application.Word;
import com.savelyev.quiz.repositories.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WordService {

    private final WordRepository wordRepository;
    public Word findWordById(Long id){
        log.info("Fetching word by ud: {}", id);
        return wordRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFound(String.format("Word with id '%d' not found", id)));
    }
}
