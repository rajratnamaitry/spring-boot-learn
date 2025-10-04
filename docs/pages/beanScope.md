---
outline: deep
---

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
  - Custom business logic
  - Setting up handles to resources (db, sockets,file etc)
- Can add custom code during bean **destruction**
  - Custom business logic
  - Clean up handles to resources (db, sockets,file etc)

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

- Use case: you may not have access to source code of third party class. `@Bean` can use this as spring bean.

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
