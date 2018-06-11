package com.example.postgresdemo.config;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {

    static final String SECRET = "zaq1xsw2cde34rfvbgt56yhnmju78ikm";//evitar acento na chave secreta.
    static final String TOKEN_PREFIX = "Auth ";//prefixo para a autenticacao
    static final String HEADER_STRING = "Authorization";//tem que ser exatamente esse nome
    static final String SIGN_UP_URL = "/users";//url permitida
    static final long EXPIRATION_TIME = 86400000L;//1 dia.

    //Rodar esta classe para pegar o valor do dia em millisegundos
    public static void main(String[] args) {
        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }


}
