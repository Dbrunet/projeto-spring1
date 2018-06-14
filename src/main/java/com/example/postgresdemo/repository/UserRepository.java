package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //busca por username
    User findByUsername(String username);

    //busca por name
    List<User> findByName(String name);

    //busca por nome ignorando uppercase e lowercase
    List<User> findByNameIgnoreCaseContaining(String name);

    //busca por profissao
    List<User> findByProfession(String profession);

    //busca por idade
    long countByAge(int age);

    //deleta por busca do nome
    List<User> deleteByName(String name);

    // multi condition. busca por profissao e idade
    List<User> findByProfessionAndAge(String profession, int age);

    //busca por profissao ignore case
    List<User> findByProfessionIgnoreCase(String profession);

    //busca por query
    @Query("select u from User u")
    List<User> getUsersCustomQuery();


    //    @Query("select u.name from User u where u.profession = :profession OR u.age = :age")//se todas as condicoes forem verdadeiras
    @Query("select u.name from User u where u.profession = :profession AND u.age = :age")
//se pelo menos uma condicao é verdadeira
    List<User> getUserSomeFieldName(@Param("profession") String profession,
                                    @Param("age") Integer age);

    //ORDER BY - ordenacao
    //use da Query ou user na assinatura do metodo do JPA
    @Query("select u.name from User u ORDER BY u.name DESC")
    List<User> findAllByOrOrderByNameAsc();

    //DISTINCT - obter valores não duplicados
    @Query("select distinct u.name from User u")
    List<User> comandDistinct();

    //WHERE - onde
    @Query("select u.name from User u where u.age = 34")
    List<User> clauseWhere();

    //Update - atualizar elementos da tabela
    @Query("UPDATE User u SET u.age = :newAge where u.id = :idUser")
    List<User> comandUpdate(@Param("idUser") Long idUser, @Param("age") Integer newAge);

    //Funcoes Agregadas
    //MIN, MAX, AVG(MEDIA), SUM, COUNT
//    @Query("select MIN (u.age) FROM User u")//valor minimo
//    @Query("select MAX (u.age) FROM User u")//valor mais auto
//    @Query("select AVG (u.age) FROM User u")//valor medio
//    @Query("select SUM (u.age) FROM User u")//soma de todos os valor
    @Query("select COUNT (u.age) FROM User")
//conta numero de linhas da coluna idade
    List<User> funcoesAgregadas();

    //BETWEEN - determinar intervalos, para filtragens
    @Query("select u.age from User u where u.age BETWEEN 30 AND 40")
    List<User> between();

    //LIKE E NOT LIKE - determinar se uma cadeia de caracteres corresponde a um padão especifico.
    //('%') - QUALQUER CADEIA DE 0 OU MAIS CARACTERES
    //('-') SUBLINHADO - QUALQUER CARACTER UNICO
    //'[]' - QUALQUER CARACTER UNICO NO INTERVALOR OU CONJUNTO ESPECIFICADO ([a-h]; [aeiou])
    //'[^]' - QUALQUER CARACTER UNICO que nao esteja NO INTERVALOR OU CONJUNTO ESPECIFICADO ([a-h]; [aeiou])
//    @Query("select u.name from User u where u.name LIKE 'S%'")//comece com a letra s
//    @Query("select u.name from User u where u.name LIKE '%S'")//termine com a letra s
    @Query("select u.name from User u where u.name LIKE '%S'")
//termine com a letra s
    List<User> like();

//    @Query("select u.name from User u where u.name LIKE '%S'")
//termine com a letra s
    List<User> join();
}
