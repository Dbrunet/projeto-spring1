package com.example.postgresdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //configurar a segurança
                .anyRequest() //para qualquer requisicao
                .authenticated()// tem que estar autenticado
                .and() //e
                .formLogin().and()
                .csrf().disable();
    }
}
