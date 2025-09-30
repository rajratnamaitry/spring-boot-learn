package com.kart.example.myKart.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restFun {
    @GetMapping("/")
    public String sayHello(){
        return "Hello world";
    }
}
