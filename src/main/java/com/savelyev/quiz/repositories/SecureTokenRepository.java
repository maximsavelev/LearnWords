package com.savelyev.quiz.repositories;

import com.savelyev.quiz.model.security.SecureToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecureTokenRepository extends CrudRepository<SecureToken,Long> {

    Optional<SecureToken> findSecureTokenByToken(String token);


}
