package com.savelyev.quiz.repositories;

import com.savelyev.quiz.model.application.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  TopicRepository extends CrudRepository<Topic,Long> {
}
