package com.savelyev.quiz.model.security;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="secure_tokens")
@Data
public class SecureToken extends BaseEntity {

    private String token;
    private LocalDateTime creationTime;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

}
