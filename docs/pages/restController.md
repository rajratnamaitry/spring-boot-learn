---
outline: deep
---

# Rest Controller

A Spring Boot component that handles HTTP requests and returns responses directly as JSON/XML.

```java
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
@RestController
@RequestMapping("/api")
public class StudentRestController {

    @GetMapping("/student/{studentId}")
    public StudentPojo getStudent(@PathVariable int studentId) {
        List<StudentPojo> theStudents = List.of(
            new StudentPojo("Poornima", "Patel"),
            new StudentPojo("Mario", "Rossi"),
            new StudentPojo("Mary", "Smith")
        );
    }
}
```

## Exception Handler

- Define error response pojo class

```java
public class StudentErrorResponse {
    private int status;
    private String message;
    private long timeStamp;

    public StudentErrorResponse() {
    }

    public StudentErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
```

- Create **StudentNotFoundException** class and extend with `RuntimeException`

```java

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}

```

- Add methods in Restcontroller to handle Errors

```java
@RequestMapping("/api")
public class StudentRestController {
    //....existing code
    @GetMapping("/student/{studentId}")
    public StudentPojo getStudent(@PathVariable int studentId) {
        // check the studentId against the list size
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    // General Error handler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
```

## Global exception handler

1. Create new `@ControllerAdvice`

    ```java
    @ControllerAdvice
    public class StudentRestExceptionHandler {
        
    }
    ```

2. Add exception method in **StudentRestExceptionHandler**

    ```java
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        // Code
    }
    // General Error handler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        // Code
    }
    ```

## Example

1. Create employee entity

    ```java
    @Entity
    @Table(name="employee")
    public class EmployeeEntity {
        @Id
        @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
        @Column(name="id")
        private int id;

        @Column(name="first_name")
        private String firstName;

        @Column(name="last_name")
        private String lastName;

        @Column(name="email")
        private String email;

        public EmployeeEntity() {
        }
        public EmployeeEntity(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        // Exisiting code...
    }
    ```

2. Create dao interface

    ```java
    public interface EmployeeDao {
        List<EmployeeEntity> findAll();
    }
    ```

3. Create dao implementation

    ```java
    @Repository
    public class EmployeeDaoJpaImp implements EmployeeDao {

        private EntityManager entityManager;

        @Autowired
        public EmployeeDaoJpaImp(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        public List<EmployeeEntity> findAll() {

        }
    // Exisiting code...
    ```

4. Create REST controller to use dao

    ```java
    @RestController
    @RequestMapping("/api/v1")
    public class EmployeeRestController {

        private EmployeeDao employeeDao;

        public EmployeeRestController(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }
        @GetMapping("/employees")
        public List<EmployeeEntity> findAll() {

        }
    // Exisiting code...
    ```
