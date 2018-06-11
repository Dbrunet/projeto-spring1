package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.UserRepository;
import com.example.postgresdemo.util.CryptPasswordUtils;
import io.swagger.annotations.ApiOperation;
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
    private final CryptPasswordUtils cryptPasswordUtils;

    @Autowired
    public UserController(UserRepository dao, CryptPasswordUtils cryptPasswordUtil) {
        this.dao = dao;
        this.cryptPasswordUtils = cryptPasswordUtil;
    }

    @GetMapping
    @ApiOperation(value = "Return a list with all users", response = User[].class)
    public ResponseEntity<?> getAll(Pageable pageable) {
        return new ResponseEntity<>(dao.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/search/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(dao.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> get(@PathVariable Long userId) {
        return new ResponseEntity<>(dao.findById(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        user.setPassword(cryptPasswordUtils.bCryptPasswordEncoder(user.getPassword()));
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
        return ResponseEntity.ok().build();
    }

}
