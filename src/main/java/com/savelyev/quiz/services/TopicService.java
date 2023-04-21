package com.savelyev.quiz.services;

import com.savelyev.quiz.exception.EntityNotFound;
import com.savelyev.quiz.model.application.Topic;
import com.savelyev.quiz.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TopicService {
    private final TopicRepository topicRepository;
    public Topic findTopicById(Long id){
        log.info("Fetching topic by id: {}", id);
        return topicRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFound(String.format("Topic with id '%d' not found", id)));
    }


}
