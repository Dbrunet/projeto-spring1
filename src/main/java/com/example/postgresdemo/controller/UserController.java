package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository dao;

    @Autowired
    public UserController(UserRepository dao) {
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        return new ResponseEntity<>(dao.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> get(@PathVariable Long userId) {
        return new ResponseEntity<>(dao.findById(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        return new ResponseEntity<>(dao.save(user), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody User userRequest) {
        return dao.findById(userRequest.getId())
                .map(user -> {
                    user.setName(userRequest.getName());
                    return new ResponseEntity<>(dao.save(user), HttpStatus.OK);
                }).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. ID " + userRequest.getId()));
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> delete(@PathVariable Long userId) {
        return dao.findById(userId)
                .map(user -> {
                    dao.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. ID " + userId));

    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        dao.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
