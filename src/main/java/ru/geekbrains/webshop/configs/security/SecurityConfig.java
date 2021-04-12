package ru.geekbrains.webshop.configs.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/persons/new", "/persons/{id}/edit").hasRole("SUPER-ADMIN")
                .antMatchers("/persons").hasAnyRole("ADMIN", "SUPER-ADMIN")
                .antMatchers("/**").hasAnyRole("ADMIN", "MANAGER", "SUPER-ADMIN")
                .and()
                .formLogin()
                .loginPage("/persons/login")
                .loginProcessingUrl("/authenticateTheUser").permitAll();
    }
}

