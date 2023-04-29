package com.savelyev.quiz.repositories;

import com.savelyev.quiz.model.application.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends CrudRepository<Word,Long> {
    Optional<Word> findWordByWordName(String wordName);
}
