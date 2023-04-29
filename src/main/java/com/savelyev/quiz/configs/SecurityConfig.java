
package com.savelyev.quiz.configs;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final private UserDetailsService userDetailsService;
    final private BCryptPasswordEncoder passwordEncoder;

    @Value("${login_url}")
    private String loginUrl;

    @Value("${logout_url}")
    private String logoutUrl ;

    @Value("${logout_success_url}")
    private String logoutSuccessUrl;

    @Value("${login_failure_url}")
    private String loginFailureUrl;

    @Value("${default_success_url}")
    private String defaultSuccessUrl;

    @Value("${error_url}")
    private String accessDeniedPage;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/change_locale/**").permitAll()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/verify/**").permitAll()
                .antMatchers("/reset/token/**").permitAll()
                .antMatchers("/reset_password/**").permitAll()
                .antMatchers("/main/**").authenticated()
                .antMatchers("/topics/**").authenticated()
                .and()
                .formLogin().loginPage(loginUrl).defaultSuccessUrl(defaultSuccessUrl).failureUrl(loginFailureUrl)
                .and().logout().logoutUrl(logoutUrl).logoutSuccessUrl(logoutSuccessUrl)
                .and().exceptionHandling().accessDeniedPage(accessDeniedPage);
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}


