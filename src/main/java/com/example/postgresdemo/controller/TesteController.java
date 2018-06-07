package com.example.postgresdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/ola")
    public String ola() {
        return "OLa";
    }
}
