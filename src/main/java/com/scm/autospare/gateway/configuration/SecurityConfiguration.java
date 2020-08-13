package com.scm.autospare.gateway.configuration;

import com.scm.autospare.gateway.service.AutospareUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AutospareUserDetailsService autospareUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autospareUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable cors and csrf
        http.cors().and().csrf().disable();

        // Entry points
        http.authorizeRequests()
                .antMatchers("/**/registeruser").permitAll()
                .antMatchers("/**/dealer/**").hasRole("ROLE_DLR")
                .antMatchers("/**/customer/**").hasRole("ROLE_CUST")
                .antMatchers(HttpMethod.GET).hasRole("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().logout().permitAll();

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling().accessDeniedPage("/login");
        http.httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
