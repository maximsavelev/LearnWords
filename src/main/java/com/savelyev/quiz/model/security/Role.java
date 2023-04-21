package com.savelyev.quiz.model.security;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role  extends BaseEntity{

    @Column(name = "role")
    private String role;
}
