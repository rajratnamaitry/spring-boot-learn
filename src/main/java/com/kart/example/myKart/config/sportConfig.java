package com.kart.example.myKart.config;

import com.kart.example.myKart.common.Coach;
import com.kart.example.myKart.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class sportConfig {
    @Bean("aqua")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
