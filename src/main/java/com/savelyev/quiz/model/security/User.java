package com.savelyev.quiz.model.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Collection;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    @Transient
    private String confirmPassword;
    private String email;

    private LocalDateTime lastSeen;
    @Column(name="enabled")
    private boolean isEnabled;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

}
