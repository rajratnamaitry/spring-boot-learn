---
outline: deep
---
# Service Layer

The service layer acts as an intermediary between controllers and data access objects (DAO), providing business logic and transaction management.

## Service Implementation

### 1. Define Service Interface

    ```java
    // filepath: src/main/java/com/kart/example/myKart/service/EmployeeService.java
    public interface EmployeeService {
        List<EmployeeEntity> findAll();
    }
    ```

### 2. Implement Service Class

    ```java
    @Service
    public class EmployeeServiceImp implements EmployeeService {
        private EmployeeDao employeeDao;

        @Autowired
        public EmployeeServiceImp(EmployeeDao theEmployeeDAO) {
            this.employeeDao = theEmployeeDAO;
        }

        @Override
        public List<EmployeeEntity> findAll() {
            return this.employeeDao.findAll();
        }
    }
    ```

### 3. Use Service in Controller

    ```java
    @RestController
    @RequestMapping("/api/v2")
    public class EmployeeServiceRestController {
        private EmployeeService employeeService;

        @Autowired
        public EmployeeServiceRestController(EmployeeService theEmployeeService) {
            this.employeeService = theEmployeeService;
        }
        
        @GetMapping("/employees")
        public List<EmployeeEntity> findAll() {
            return employeeService.findAll();
        }
    }
    ```

## Key Concepts

1. **@Service Annotation**
   - Marks class as service layer component
   - Enables component scanning
   - Allows autowiring

2. **Constructor Injection**
   - Recommended approach for dependency injection
   - Makes dependencies explicit
   - Supports final fields

3. **Interface-based Design**
   - Defines contract for service operations
   - Enables loose coupling
   - Facilitates testing and maintenance

:::tip Best Practices

- Use constructor injection over field injection
- Create service interfaces for better abstraction
- Keep business logic in service layer, not in controllers
- Use meaningful method names that reflect business operations
:::

## @Repository vs @Service Annotations

Here's the key differences between these Spring stereotypes:

## @Repository

- Used for DAO/persistence layer
- Marks the class as a database access component
- Translates JDBC exceptions into Spring's DataAccessException
- Typically works directly with database/data access code
- Usually contains queries and database operations

## @Service

- Used for business logic layer
- Marks the class as a service provider
- Contains business rules and calculations
- Coordinates multiple repositories
- Acts as a facade over repositories
- Does not handle exceptions translation