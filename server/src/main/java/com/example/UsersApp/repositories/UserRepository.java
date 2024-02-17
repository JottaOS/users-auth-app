package com.example.UsersApp.repositories;

import com.example.UsersApp.models.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
