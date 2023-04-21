package com.savelyev.quiz.repositories;

import com.savelyev.quiz.model.application.Theme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  ThemeRepository extends CrudRepository<Theme,Long> {
    List<Theme> findAll();
}
