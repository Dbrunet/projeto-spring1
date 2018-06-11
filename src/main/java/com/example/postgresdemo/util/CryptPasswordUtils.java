package com.example.postgresdemo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
public class CryptPasswordUtils {

    public String bCryptPasswordEncoder(@NotEmpty String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
