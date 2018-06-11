package com.example.postgresdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
