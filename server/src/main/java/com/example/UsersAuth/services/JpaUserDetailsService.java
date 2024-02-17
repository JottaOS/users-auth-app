package com.example.UsersAuth.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaUserDetailsService implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (!username.equals("admin")) {
      throw new UsernameNotFoundException("Username " + username + " no existe en el sistema");
    }

    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

    return new User(username,
            "$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS",
            true,
            true,
            true,
            true,
            grantedAuthorities);
  }
}
