# Rest Controller

A Spring Boot component that handles HTTP requests and returns responses directly as JSON/XML.

```JAVA
@RestController
public class restFun {
    @GetMapping("/")
    public String sayHello(){
        return "Hello world";
    }
}
```

## Java POJO

Plain Old Java Objects are simple classes used for data encapsulation, typically containing private fields with public getters/setters.

### Example POJO

```java
public class StudentPojo {
    private String firstName;
    private String lastName;

    // No-args constructor
    public StudentPojo() {
    }

    // Constructor with args
    public StudentPojo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
```

:::tip
POJOs are commonly used for:

- Data Transfer Objects (DTOs)
- Entity classes
- Request/Response models

:::

## Request Mapping

Maps HTTP requests to specific handler methods using path patterns and HTTP methods.

```java
@RestController
@RequestMapping("/api")
public class StudentRestController {

    @GetMapping("/students")
    public List<StudentPojo> getStudents() {
        List<StudentPojo> theStudents = List.of(
            new StudentPojo("Poornima", "Patel"),
            new StudentPojo("Mario", "Rossi"),
            new StudentPojo("Mary", "Smith")
        );
        return theStudents;
    }
    
}

```

## Path Variable

Extracts values from the URL path and binds them to method parameters using `@PathVariable`.

```java
    @GetMapping("/student/{studentId}")
    public StudentPojo getStudent(@PathVariable int studentId) {
        List<StudentPojo> theStudents = List.of(
            new StudentPojo("Poornima", "Patel"),
            new StudentPojo("Mario", "Rossi"),
            new StudentPojo("Mary", "Smith")
        );
```
