package com.savelyev.quiz.services.security;

import com.savelyev.quiz.model.security.Role;
import com.savelyev.quiz.model.security.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    final private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)  {
        User user = userService.findUserByName(username);
        checkUserVerification(user);
        user.setLastSeen(LocalDateTime.now());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }



    private void checkUserVerification(User user) {
        if (!user.isEnabled()) {
            throw new UsernameNotFoundException(String.format("User '%s' is not activated", user.getUsername()));
        }
    }

}
