package com.example.UsersAuth.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

  @Autowired
  private AuthenticationConfiguration authenticationConfiguration;


  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(authRules -> authRules
                    .requestMatchers(HttpMethod.GET, "/users").permitAll()
                    .anyRequest().authenticated())
            .csrf(config -> config.disable())
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
  }
}
