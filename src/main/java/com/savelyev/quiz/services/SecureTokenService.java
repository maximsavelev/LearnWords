package com.savelyev.quiz.services;

import com.savelyev.quiz.exception.IncorrectTokenTypeException;
import com.savelyev.quiz.exception.TokenHasExpiredException;
import com.savelyev.quiz.model.security.SecureToken;
import com.savelyev.quiz.model.security.TokenType;
import com.savelyev.quiz.model.security.User;
import com.savelyev.quiz.repositories.SecureTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class SecureTokenService {
    private final SecureTokenRepository secureTokenRepository;

    public SecureToken generateVerifyToken(User user) {
        return generateSecureToken(user,TokenType.EMAIL_CONFIRM);
    }

    public SecureToken generatePasswordToken(User user){
        return generateSecureToken(user,TokenType.PASSWORD_RESET);
    }

    private SecureToken generateSecureToken(User user, TokenType tokenType) {
        String tokenValue = String.valueOf(UUID.randomUUID());
        SecureToken secureToken = new SecureToken();
        secureToken.setToken(tokenValue);
        secureToken.setUser(user);
        secureToken.setTokenType(tokenType);
        secureToken.setCreationTime(LocalDateTime.now());
        saveSecureToken(secureToken);
        return secureToken;
    }


    public void saveSecureToken(SecureToken token) {
        secureTokenRepository.save(token);
    }

    public SecureToken findByToken(String token) throws TokenHasExpiredException {
        return secureTokenRepository.findSecureTokenByToken(token).orElseThrow(TokenHasExpiredException::new);
    }

    public void deleteToken(SecureToken token) {
        secureTokenRepository.delete(token);
    }

    public void checkTokenType(SecureToken token, TokenType tokenType) throws IncorrectTokenTypeException {
        if (!token.getTokenType().equals(tokenType)) {
            throw new IncorrectTokenTypeException();
        }
    }


  /*  @Scheduled(fixedRate = 15000)
    public void deleteAllTokensByTime() {
        secureTokenRepository.deleteAllByTimerLessThan(LocalDateTime.now());
    }*/

}
