
# JPA (Java Persistence API)

- Standard API for ORM
- Only a specification
  - Defines a set of interface
- Maintain portable , flexible code

## Hibernate

- Handles all of the low level sql
- Minimizes JDBC code for development
- Provides Object to Relational Mapping (ORM)
Hibernate/JPA uses JDBC for all database communications

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker
spring.datasource.username=springstudent
spring.datasource.password=springstudent
```

## Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

## Automatic data source configuration

- Based on config, Spring boot will automatically create the beans: **DataSource** , **EntityManager**
- Inject these into app, e.g. DAO

## Command Line Runner

- From Spring boot framework
- Executed after the spring beans have been loaded
- Declare `@Bean` add method with **CommandLineRunner** interface
- Add Java lambda expression

```java
@SpringBootApplication
public class MyKartApplication {
    @Bean
    public CommandLineRunner commandLineRunner(String[] args) {
        return runner -> {
            System.out.println("Command runner");
        };
    };
}
```

## Entity Class

- Annotated with @Entity
- Must have a public or protected no-argu constructor

## Setps

1. Map class to db table

    ```java
    @Entity
    @Table(name="student")
    public class Student {
    }
    ```

2. Map fields to db columns

    ```java
    @Entity
    @Table(name="student")
    public class Student {
        // Fields
        @Id
        @Column(name="id")
        private int id;

        @Column(name="first_name")
        private String firstName;

        @Column(name="last_name")
        private String lastName;

        @Column(name="email")
        private String email;
    }
    ```

3. Generation strategies

    |Name|Description|
    |---|---|
    |GenerationType.IDENTITY|Primary key using identity|
    |GenerationType.AUTO| Pick an appropriate strategy |
    |GenerationType.SEQUENCE|Primary key using db sequence|
    |GenerationType.TABLE|Primary key using an underlying db table|
    |GenerationType.UUID|Primary key using a globally unique id|

    ```java
    @Entity
    @Table(name="student")
    public class Student {
        // Fields
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private int id;
    }
    ```

## JPA Entity Manager

- Jpa entity manager needs a data source
- The data source defines db connection info
- Jpa Entity manager and Data source are automatically created by spring boot (application.properties)

```flowchart

[Student DAO] <---> [Entity Manager] <---> [Data Source] <---> [SQL]

```

## Step to DAO implementation

1. Define DAO interface

```java

```

2. DAO implementation

```java
```

### @Transactional

- Auto begin and end a transaction for JPA code

### @Repository

- Specialized annotation for repo
- Supports component scanning
- Translates JDBC expections
