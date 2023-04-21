package com.savelyev.quiz.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Entity doesn't exists")
@NoArgsConstructor
public class EntityNotFound extends RuntimeException {
    public EntityNotFound(String message) {
        super(message);
    }
}
