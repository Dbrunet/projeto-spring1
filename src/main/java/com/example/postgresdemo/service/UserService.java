package com.example.postgresdemo.service;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.User;
import com.example.postgresdemo.repository.UserRepository;
import com.example.postgresdemo.util.CryptPasswordUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final CryptPasswordUtils cryptPasswordUtils;

    public UserService(UserRepository repository, CryptPasswordUtils cryptPasswordUtils) {
        this.cryptPasswordUtils = cryptPasswordUtils;
        this.repository = repository;
    }

    @PostConstruct
    public void initDB() {
        List<User> users = new ArrayList<>();
        users.add(new User("jose", "jose@email.com", "josedasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Analista", 34));
        users.add(new User("joao", "joao@email.com", "joaodasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Medico", 35));
        users.add(new User("marcos", "marcos@email.com", "marcosdasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Arquiteto", 36));
        users.add(new User("mauricio", "mauricio@email.com", "mauriciodasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Enfermero", 35));
        users.add(new User("felipe", "felipe@email.com", "felipedasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Advogado", 24));
        users.add(new User("souza", "souza@email.com", "souzadasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Advogado", 24));
        users.add(new User("carlos", "carlos@email.com", "calordasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Enfermero", 26));
        users.add(new User("antonio", "antonio@email.com", "antoniodasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Enfermero", 26));
        users.add(new User("douglas", "douglas@email.com", "douglasdasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Advogado", 36));
        users.add(new User("diego", "diego@email.com", "diegodasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Medico", 24));
        users.add(new User("wanderson", "wanderson@email.com", "wandersondasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Advogado", 24));
        users.add(new User("akiles", "akiles@email.com", "akilesdasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Arquiteto", 34));
        users.add(new User("batman", "batman@email.com", "batmandasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Arquiteto", 34));
        users.add(new User("honorio", "honorio@email.com", "honoriodasilva", cryptPasswordUtils.bCryptPasswordEncoder("123"), false, "Analista", 24));
        repository.saveAll(users);
    }

    public ResponseEntity<?> save(User user) {
        user.setPassword(cryptPasswordUtils.bCryptPasswordEncoder(user.getPassword()));
        return new ResponseEntity<>(repository.save(user), HttpStatus.OK);
    }

    public ResponseEntity<?> update(User userRequest) {
        return repository.findById(userRequest.getId())
                .map(user -> {
                    user.setName(userRequest.getName());
                    return new ResponseEntity<>(repository.save(user), HttpStatus.OK);
                }).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. ID " + userRequest.getId()));
    }

    public ResponseEntity<?> delete(Long userId) {
        return repository.findById(userId)
                .map(user -> {
                    repository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. ID " + userId));
    }

    public ResponseEntity<?> deleteAll() {
        repository.deleteAll();
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    // pagination
    public ResponseEntity<?> getPaginatedUser(int page, int size, String field) {
        return new ResponseEntity<>(repository.findAll(PageRequest.of(page, size, Sort.by(field))), HttpStatus.OK);
    }

    public ResponseEntity<?> getUserByProfession(String profession) {
        return new ResponseEntity<>(repository.findByProfession(profession), HttpStatus.OK);
    }

    public ResponseEntity<?> getUserByName(String name) {
        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
    }

    public ResponseEntity<?> getUserById(long id) {
        return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public ResponseEntity<?> getUserByNameIgnoreCase(String name) {
        return new ResponseEntity<>(repository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    public ResponseEntity<?> getCounts(int age) {
        return new ResponseEntity<>(repository.countByAge(age), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUser(String name) {
        return new ResponseEntity<>(repository.deleteByName(name), HttpStatus.OK);
    }

    public ResponseEntity<?> findByMultiCondition(String profession, int age) {
        return new ResponseEntity<>(repository.findByProfessionAndAge(profession, age), HttpStatus.OK);
    }

    public ResponseEntity<?> getUsersIgnoreCase(String profession) {
        return new ResponseEntity<>(repository.findByProfessionIgnoreCase(profession), HttpStatus.OK);
    }

    // sort
    public ResponseEntity<?> getUserSort(String field) {
        return new ResponseEntity<>(repository.findAll(Sort.by(field)), HttpStatus.OK);
    }

    // custom Query
    public ResponseEntity<?> getUsersCustomQuery() {
        return new ResponseEntity<>(repository.getUsersCustomQuery(), HttpStatus.OK);
    }

    // custom Query
    public ResponseEntity<?> getUserFieldName(String profession, Integer age) {
        return new ResponseEntity<>(repository.getUserSomeFieldName(profession, age), HttpStatus.OK);
    }
}
