package com.savelyev.quiz.repositories.security;

import com.savelyev.quiz.model.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUsername(String name);

    boolean existsByEmail(String email);


    Optional<User> findUserByUsername(String s);

    Optional<User> findUserByEmail(String email);

    @Query(value = "select u.id, u.email, u.enabled, u.last_seen, u.password, u.username, u.created_at, u.updated_at" +
            " from users u join records r on  u.id = r.user_id group by u.id order by avg(percentage) desc",
            nativeQuery = true)
    Page<User> ratingUsers(Pageable pageable);

    @Query(value =
            "select number from(" +
                    "select row_number()   over (order by avg(percentage) desc) as number, u.id" +
            " from users u join records r on  u.id = r.user_id   group by u.id) as rating where id = :id",
            nativeQuery = true)
    Long getUsersRatingPlace(Long id);

    @Query("Select u from User u where lower(u.username)  like %:username%")
    Page<User> findUsersByUsername(String username, Pageable pageable);

}
