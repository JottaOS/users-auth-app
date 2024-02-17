package com.example.UsersApp.services;

import com.example.UsersApp.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

  List<User> findAll();

  Optional<User> findById(Long id);

  User save(User user);

  Optional<User> update(User user, Long id);

  void delete(Long id);
}
