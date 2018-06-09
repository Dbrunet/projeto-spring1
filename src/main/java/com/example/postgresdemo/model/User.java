package com.example.postgresdemo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @NotEmpty(message = "O campo nome do usuario é obrigatório")
    private String name;
    @NotEmpty(message = "O campo email do usuario é obrigatório")
    @Email(message = "Digite um email válido")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
