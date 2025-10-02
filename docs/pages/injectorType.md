# Dependency Injection Types
dependency resolution spring creats instance of `@Component` first then create instance of `@Controller` and inject the `@Component`
## Constructor Injection
- Use this when you have required dependencies
- Generally recommended by spring.io
- Dependencies are declared final
- Ensures required dependencies are provided at initialization

### Example Implementation
1. Define the Coach interface:
```java
// Coach.java
public interface Coach {
    String getGymDetails();
}
```
2. Implement the Cricket Coach:
```java
// CricketCoach.java
@Component
public class CricketCoach implements Coach {
    @Override
    public String getGymDetails() {
        return "Daily 24*7 Gym !!";
    }
    public CricketCoach(){
        System.out.println("In constructor"+getClass().getSimpleName());
    }
}
```
3. Controller with constructor injection types:

```java
// CoachController.java
@RestController
public class CoachController {
    private final Coach myTheCoach;
    @Autowired
    public CoachController(Coach theCoach){
        System.out.println("In Constructor"+getClass().getSimpleName());
        myTheCoach = theCoach;
    }
    @GetMapping("/dailyWorkOut")
    public String getDailyWorkOut() {
        return myTheCoach.getGymDetails();
    }
}
```
## Setter Injection
- Use this when you have optional dependencies
- If dependency is not provided, your app can provide reasonable default logic
- More flexible but less secure than constructor injection

### Example Implementation

1. Define the Coach interface:
2. Implement the Cricket Coach:
3. Controller with set injection types:
`@Autowired`
```java
// CoachController.java
@RestController
public class CoachController {
    private final Coach myTheCoach;
    @Autowired
    public void setDependency(Coach theCoach){
        System.out.println("In Constructor"+getClass().getSimpleName());
        myTheCoach = theCoach;
    }
    @GetMapping("/dailyWorkOut")
    public String getDailyWorkOut() {
        return myTheCoach.getGymDetails();
    }
}
```
## Field Injection
::: warning
Not recommoned by spring.io dev team. makes the code harder to UT.
:::
### Example Implementation
1. Define the Coach interface:
2. Implement the Cricket Coach:
3. Controller with field injection types:
`@Autowired`
```java
// CoachController.java
@RestController
public class CoachController {
    @Autowired
    private final Coach myTheCoach;
}
```
## Qualifiers Injection
- Has higher priority over primary annotation
- More specific
- If same interface implemented in multiple components then it will throw error. `required a single bean, but 4 were found`

### Example Implementation
1. Define the Coach interface:
2. Implement the Cricket Coach:
3. Controller with Qualifiers injection types:
`@Autowired`
4. keep same name as class, first character lower-case
```java
@RestController
public class CoachController {
    @Autowired
    public CoachController(
            @Qualifier("cricketCoach") Coach theCoach
    ){
        System.out.println("In Constructor"+getClass().getSimpleName());
        myTheCoach = theCoach;
    }
}
```
## Primary 
- Can have only one for multiple implementations.
- No need to use Qualifier Annotation, @Primary will pick Component.

```java

@Component
@Primary
public class BaseBallCoach implements Coach{}

@Component
public class CricketCoach implements Coach{}

@Component
public class FootBallCoach implements Coach{}
```
## Lazy 
* By default,when application starts all beans are initialized
    - @Component etc..
    - Constructor get invoke
### Advantages
- Bean is only initialized if needed for dependency injection
- Faster startup time
### Disadvantages
- `@RestController` not created until requested.
- make sure enough memory for all beans
```java
@Component
@Primary
@Lazy
public class BaseBallCoach implements Coach{}
```
### Global lazy setup
```properties
spring.main-lazy-initialization=true
```

## How Auto wiring works ?
Spring will look for a class that matches:
- Matches by type: class or interface
- Scans for `@Components`
- Injects dependencies automatically

|Scope|Description|
|--|--|
|singleton|Creates single shared instance|
|prototype|Create a new bean instance for each|
|request|Scoped to an HTTP request, only for web apps|
|session|Scoped to an HTTP session, only for web apps|
|application|Scoped to a web app servietContext, only for web apps|
|websocket|Scoped to a web socket, only for web apps|
## Singleton bean
- Both refer same instance
```java
// @component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CricketCoach implements Coach{
}
```
```java
@RestController
public class CoachController {
    private final Coach myTheCoach;
    private final Coach myAnotherCoach;
    @Autowired
    public CoachController(
            @Qualifier("cricketCoach") Coach theCoach,
            @Qualifier("cricketCoach") Coach theAnotherCoach
    ){
        System.out.println("In Constructor"+getClass().getSimpleName());
        myTheCoach = theCoach;
        myAnotherCoach = theAnotherCoach;
    }
}
```
## Prototype
- new bean instance for each injection
```java
// @component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CricketCoach implements Coach{
}

// @RestController
@GetMapping("/check")
public String checkInst(){
    return "Comparing beans: Is same instance "+ (myTheCoach == myAnotherCoach);
}
```
# Bean Scope
Spring container creates only one instance of the bean, by default
- Cached in memory
- All dependency injection for the bean will refer the same bean
## Bean Life cycle
```mermaid
flowchart LR
    A[Container] --> B[Bean Instantiated]
    B --> C[Dependencies Injected]
    C --> D[Internal Spring Processing]
    D --> E[Custom Init Method]
    E --> F[Bean Ready for Use]
    F --> G[Container Shutdown]
    G --> H[Custom Destroy Method]
    H --> I[Stop]
```
### Methods/Hooks
- Can add custom code during bean **initialization**
    * Custom business logic
    * Setting up handles to resources (db, sockets,file etc)
- Can add custom code during bean **destruction**
    * Custom business logic
    * Clean up handles to resources (db, sockets,file etc)

```java
@Component
public class CricketCoach implements Coach {
    
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("In doMyStartupStuff(): " + getClass().getSimpleName());
    }
    
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("In doMyCleanupStuff(): " + getClass().getSimpleName());
    }
}
```
### Life Cycle Phases

| Phase | Description |
|-------|-------------|
| Instantiation | Spring creates the bean instance |
| Properties Set | Dependencies are injected |
| Init Method | Custom initialization code runs |
| In Service | Bean is ready for use |
| Destroy | Custom cleanup code runs |

:::tip
Use `@PostConstruct` and `@PreDestroy` annotations to hook into bean lifecycle events
:::
:::warning
For **prototype** scoped beans, Spring does not call the destroy method
:::
## Config Bean
* Use case: you may not have access to source code of third party class. `@Bean` can use this as spring bean.
1. Create Config package
2. Add `@Configuration` annotation and Add `@Bean` annotation
    - `@Bean("customName")` can provide id
```java
@Configuration
public class sportConfig {
    @Bean("aqua")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
```
3. Inject bean into controller, no require component
```java
// Note: no annotation
//@Component
public class SwimCoach implements Coach{
    public SwimCoach(){
        System.out.println("in constructor"+getClass().getSimpleName());
    }
}
@RestController
public class CoachController {
    private final Coach myTheCoach;
    private final Coach myAnotherCoach;
    @Autowired
    public CoachController(
            @Qualifier("cricketCoach") Coach theCoach,
            @Qualifier("aqua") Coach theAnotherCoach
    ){
        System.out.println("In Constructor"+getClass().getSimpleName());
        myTheCoach = theCoach;
        myAnotherCoach = theAnotherCoach;
    }
}
```