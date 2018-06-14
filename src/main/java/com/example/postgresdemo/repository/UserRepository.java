package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //busca por nome
    User findByUsername(String username);

    List<User> findByName(String name);

    //busca por nome ignorando uppercase e lowercase
    List<User> findByNameIgnoreCaseContaining(String name);

    //busca por profissao
    List<User> findByProfession(String profession);

    //busca por idade
    long countByAge(int age);

    //deleta por busca do nome
    List<User> deleteByName(String name);

    // multi condition
    //busca por profissao e idade
    List<User> findByProfessionAndAge(String profession, int age);

    // ignore case
    List<User> findByProfessionIgnoreCase(String profession);

    //busca por query
    @Query("select u from User u")
    List<User> getUsersCustomQuery();

    @Query("select u.name from User u where u.profession = :profession AND u.age = :age")
    List<User> getUserSomeFieldName(@Param("profession") String profession,
                                @Param("age") Integer age);
}
