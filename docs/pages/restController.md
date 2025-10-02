# Rest Controller
- @RestController set up rest controller
- @GetMapping handle get request
```JAVA
@RestController
public class restFun {
    @GetMapping("/")
    public String sayHello(){
        return "Hello world";
    }
}
```
