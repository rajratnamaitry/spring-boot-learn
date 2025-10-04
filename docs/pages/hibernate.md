---
outline: deep
---
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
    //file:  StudentDao.java
    public interface StudentDao {
        void save(Student theStudent);
    }
    ```

2. DAO implementation
    - Define entity manager
    - Set up constructor
    - Use `entityManager`

    ```java

    @Repository
    public class StudentRepo implements StudentDao {
        // Define entity manager
        private EntityManager entityManager;

        // Set up constructor injection
        @Autowired
        public StudentRepo(EntityManager theEntityManager) {
            this.entityManager = theEntityManager;
        }

        @Override
        @Transactional
        public void save(Student theStudent) {
            System.out.println("Student saved: " + theStudent);
            this.entityManager.persist(theStudent);
        }
    }
    ```

### @Transactional

- Auto begin and end a transaction for JPA code
- `@Transactional` for save,update,delete method only

### @Repository

- Specialized annotation for repo
- Supports component scanning
- Translates JDBC expections
- `@Repository` Its primary purpose is to manage data persistence and retrieval operations, typically interacting with a database.

## JPQL

- Similar in concept to SQL
- Is based on entity name and entity fields

### JPQL Named parameters

- `theData` can be used as parameter

```java
    @Override
    public Student findByLastName(String theLastName) {
        TypedQuery<Student> theQuery = this.entityManager.createQuery("from Student where lastName=:theData", Student.class);
        theQuery.setParameter("theData", theLastName);
        List<Student> students = theQuery.getResultList();
        if (students.size() == 0) {
            return null;
        }
        return students.get(0);
    }
```

### JPQL select clause

- For JPQL, the "select" clause is required.

```java
    @Override
    public List<Student> getAllStudent() {
        TypedQuery<Student> theQuery = this.entityManager.createQuery("from Student order by firstName", Student.class);
        return theQuery.getResultList();
    }
```

## JPA Configuration

- JPA/Hibernate will **drop** tables then **create** them
- Based on the JPA/Hibernate annotations in your java code

|Property|Description|
|--|--|
|none| No action |
|create| Db tables are dropped first and then created |
|create-drop|Db tables are dropped first and then created. on application shutdown, drop the db tables|
|validate|Validate the db tables schema|
|update| Update the database table schema|

```properties
spring.jpa.hibernate.ddl-auto=create
```

:::info
if you want to create table once and keep the data, use:**update**
however, will alter db scheme based on latest code update
:::

:::danger
When db tables are dropped, all data is lost
:::
:::danger
Dont use **create** on production db!!
:::
