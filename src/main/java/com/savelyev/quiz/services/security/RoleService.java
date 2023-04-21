package com.savelyev.quiz.services.security;

import com.savelyev.quiz.model.security.Role;
import com.savelyev.quiz.repositories.security.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findRoleByRole(String role){
        log.info("Fetching role by name: {}",role);
       return roleRepository.findRoleByRole(role);
    }
}
