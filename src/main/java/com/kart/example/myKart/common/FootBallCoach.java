package com.kart.example.myKart.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class FootBallCoach implements Coach{
    @Override
    public String getGymDetails() {
        return "Iron legs";
    }
    public FootBallCoach(){
        System.out.println("In constructor"+getClass().getSimpleName());
    }
}
