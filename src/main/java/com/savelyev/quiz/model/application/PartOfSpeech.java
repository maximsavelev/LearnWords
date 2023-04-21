package com.savelyev.quiz.model.application;

import com.savelyev.quiz.model.security.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "types")
public class PartOfSpeech extends BaseEntity {

    private String name;
}
