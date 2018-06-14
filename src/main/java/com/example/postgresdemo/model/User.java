package com.example.postgresdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class User extends AbstractEntity {

    @NotEmpty(message = "O campo nome do usuario é obrigatório")
    private String name;

    @NotEmpty(message = "O campo email do usuario é obrigatório")
    @Email(message = "Digite um email válido")
    private String email;

    @NotEmpty
    @Column(unique = true)
    private String username;

    @NotEmpty
    private String password;

    private boolean admin;

    private String profession;

    private int age;
}
