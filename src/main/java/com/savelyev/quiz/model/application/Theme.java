package com.savelyev.quiz.model.application;

import com.savelyev.quiz.model.security.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "themes")
@Data
public class Theme extends BaseEntity {
    private String name;
    @OneToMany(mappedBy="theme")
    List<Topic> topics;
}
