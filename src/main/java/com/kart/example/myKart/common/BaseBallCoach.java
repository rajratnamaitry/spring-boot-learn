package com.kart.example.myKart.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class BaseBallCoach implements Coach{
    @Override
    public String getGymDetails() {
        return "Do home run !!";
    }
    public BaseBallCoach(){
        System.out.println("In constructor"+getClass().getSimpleName());
    }
}
