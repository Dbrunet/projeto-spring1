package com.example.postgresdemo.controller;

import com.example.postgresdemo.model.User;
import com.example.postgresdemo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "Retorna a lista de todos os usuários", response = User[].class)
    public ResponseEntity<?> getAll() {
        return userService.getPaginatedUser(0, 3, "name");
    }

    @GetMapping(path = "/name/search/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        return userService.getUserByName(name);
    }

    @GetMapping(path = "/profession/search/{profession}")
    public ResponseEntity<?> getByProfession(@PathVariable("profession") String profession) {
        return userService.getUserByProfession(profession);
    }

    @GetMapping(path = "/counts/{age}")
    public ResponseEntity<?> countByAge(@PathVariable("age") Integer age) {
        return userService.getCounts(age);
    }

    @GetMapping(path = "/some-name/{profession}/{age}")
    public ResponseEntity<?> getUserFieldName(@PathVariable("profession") String profession, @PathVariable("age") Integer age) {
        return userService.getUserFieldName(profession, age);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> get(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody User userRequest) {
        return userService.update(userRequest);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> delete(@PathVariable Long userId) {
        return userService.delete(userId);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        return userService.deleteAll();
    }

}
