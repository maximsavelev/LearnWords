package com.savelyev.quiz.model.application;

import com.savelyev.quiz.model.security.BaseEntity;
import com.savelyev.quiz.model.security.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "words")
public class Word extends BaseEntity {

    private String wordName;
    private String meaning;

    private String transcription;

    private String example;

    private String audio;

    private String picture;


    @ManyToOne
    private PartOfSpeech partOfSpeech;
}
