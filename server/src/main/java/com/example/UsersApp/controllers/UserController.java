package com.example.UsersApp.controllers;

import com.example.UsersApp.models.entity.User;
import com.example.UsersApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping
  public List<User> findAll(){
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id){
    Optional<User> userOptional = service.findById(id);
    if(userOptional.isPresent()){
      return ResponseEntity.ok(userOptional.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
    if(result.hasErrors()){
      return validation(result);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody User user, BindingResult result){
    Optional<User> userOptional = service.update(user, id);
    if(result.hasErrors()){
      return validation(result);
    }
    if(userOptional.isPresent()){
      return ResponseEntity.ok(userOptional.get());
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id){
    Optional<User> userOptional = service.findById(id);
    if(userOptional.isPresent()){
      service.delete(id);
      return ResponseEntity.ok().build();
    }

    return ResponseEntity.notFound().build();
  }

  private ResponseEntity<?> validation(BindingResult result) {
    Map<String, String> errors = new HashMap<>();
    result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));

    return ResponseEntity.badRequest().body(errors);
  }
}
