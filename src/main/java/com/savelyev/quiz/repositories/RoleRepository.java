package com.savelyev.quiz.repositories;

import com.savelyev.quiz.model.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findRoleByRole(String name);
}
