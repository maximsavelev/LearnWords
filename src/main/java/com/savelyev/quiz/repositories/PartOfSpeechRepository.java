package com.savelyev.quiz.repositories;


import com.savelyev.quiz.model.application.PartOfSpeech;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartOfSpeechRepository extends CrudRepository<PartOfSpeech,Long> {
}
