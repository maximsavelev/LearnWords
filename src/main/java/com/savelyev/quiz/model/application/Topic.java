package com.savelyev.quiz.model.application;

import com.savelyev.quiz.model.security.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Table(name = "topics")
@Entity
@EqualsAndHashCode(callSuper = true)
@Data

public class Topic extends BaseEntity {
    private String name;
    private String preview;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "topic_list",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id"))
    private List<Word> words;


}
