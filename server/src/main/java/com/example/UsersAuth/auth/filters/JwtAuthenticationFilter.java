package com.example.UsersAuth.auth.filters;

import com.example.UsersAuth.models.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    User user = null;
    String username = null;
    String password = null;

    try {
      user = new ObjectMapper().readValue(request.getInputStream(), User.class);
      username = user.getUsername();
      password = user.getPassword();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

    return authenticationManager.authenticate(authToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
    String originalInput = "algun_token_con_frase_secreta." + username;
    String token = Base64.getEncoder().encodeToString(originalInput.getBytes());

    response.addHeader("Authorization", "Bearer " + token);
    Map<String, Object> body = new HashMap<>();
    body.put("token", token);
    body.put("message", "Ha iniciado sesión exitosamente");
    body.put("username", username);

    response.getWriter().write(new ObjectMapper().writeValueAsString(body));
    response.setStatus(HttpStatus.OK.value());
    response.setContentType("application/json");
    response.flushBuffer();
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    Map<String, Object> body = new HashMap<>();
    body.put("error", failed.getMessage());
    body.put("message", "Usuario o contraseña incorrecta");

    response.getWriter().write(new ObjectMapper().writeValueAsString(body));
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");
  }
}
