package com.kart.example.myKart.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
// import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
// import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//Note Primary injector
@Component
@Primary
// Note: Lazy it will not call un till requested
@Lazy
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CricketCoach implements Coach{

    @Override
    public String getGymDetails() {
        return "Daily 24*7 Gym !!";
    }
    @PostConstruct()
    public void doSomeThingPostConst(){
        System.out.println("Post call");
    }
//    Note : For "prototype" scoped beans, Spring does not call the destroy method. Gasp!
    @PreDestroy()
    public void doSomeThingPreDest(){
        System.out.println("Pre destroy");
    }
    public CricketCoach(){
        System.out.println("In constructor"+getClass().getSimpleName());
    }
}
