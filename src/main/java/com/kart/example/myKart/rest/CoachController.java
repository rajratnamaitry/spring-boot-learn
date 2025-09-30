package com.kart.example.myKart.rest;

import com.kart.example.myKart.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoachController {
//    Note: Field injector
//    @Autowired
    private final Coach myTheCoach;
    private final Coach myAnotherCoach;
// Note: constructor injector. Qualifier injector
    @Autowired
    public CoachController(
            @Qualifier("cricketCoach") Coach theCoach,
            @Qualifier("aqua") Coach theAnotherCoach
    ){
        System.out.println("In Constructor"+getClass().getSimpleName());
        myTheCoach = theCoach;
        myAnotherCoach = theAnotherCoach;
    }
    @GetMapping("/check")
    public String checkInst(){
        return "Comparing beans: Is same instance "+ (myTheCoach == myAnotherCoach);
    }
//  Note : Set injector
//    @Autowired
//    public setDependency(@Qualifier("cricketCoach") Coach theCoach){
//        myCoach = theCoach;
//    }
    @GetMapping("/dailyWorkOut")
    public String getDailyWorkOut() {
        return myTheCoach.getGymDetails();
    }
}
