package com.example.UsersAuth.repositories;

import com.example.UsersAuth.models.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
