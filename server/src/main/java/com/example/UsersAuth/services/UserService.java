package com.example.UsersAuth.services;

import com.example.UsersAuth.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

  List<User> findAll();

  Optional<User> findById(Long id);

  User save(User user);

  Optional<User> update(User user, Long id);

  void delete(Long id);
}
