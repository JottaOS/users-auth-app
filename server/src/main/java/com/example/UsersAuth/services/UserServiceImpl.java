package com.example.UsersAuth.services;

import com.example.UsersAuth.models.entity.User;
import com.example.UsersAuth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository repository;

  @Override
  @Transactional(readOnly = true)
  public List<User> findAll() {
    return (List<User>) repository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<User> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public User save(User user) {
    return repository.save(user);
  }

  @Override
  public Optional<User> update(User user, Long id) {
    Optional<User> userOptional = findById(id);

    if(userOptional.isPresent()){
      User userDb = userOptional.get();
      userDb.setUsername(user.getUsername());
      userDb.setEmail(user.getEmail());
      save(userDb);
      return Optional.of(userDb);
    }

    return Optional.empty();
  }

  @Override
  @Transactional
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
