package com.kart.example.myKart.common;

import org.springframework.stereotype.Component;
// Note: no annotation
//@Component
public class SwimCoach implements Coach{
    public SwimCoach(){
        System.out.println("in constructor"+getClass().getSimpleName());
    }

    @Override
    public String getGymDetails() {
        return "Swim 100 rounds";
    }
}
