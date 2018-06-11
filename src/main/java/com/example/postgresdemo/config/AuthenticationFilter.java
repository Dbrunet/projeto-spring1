package com.example.postgresdemo.config;

import com.example.postgresdemo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.example.postgresdemo.config.SecurityConstants.*;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //tentativa de autenticacao. Se a autenticacao for bem sucedida, retornamos o Authentication e o spring chama o successfulAuthentication
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try { //Dentro do Requeste tem os dados do User. Vamos pegar o user do request
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            //retornarmos o authentication, que é a autencicação.
            return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
        String token = Jwts
                .builder()
                .setSubject(username)//
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//expiracao
                .signWith(SignatureAlgorithm.HS512, SECRET)//cripto
                .compact();
        String finalToken = TOKEN_PREFIX + token;
        response.getWriter().write(finalToken);
        response.addHeader(HEADER_STRING, finalToken);
    }
}
