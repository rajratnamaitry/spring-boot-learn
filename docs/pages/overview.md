---
outline: deep
---
# Overview

- Minimize the amount of manual configuration
- Perform auto-configuration based on props files and JAR classpath.
- Help to resolve dependency (Maven or Gradle)
- Provide an embedded HTTP Server
  - Tomcat , Jetty, Undertow....
  - JAR file includes application code and server.

```java
> java -jar myapp.jar
```

## Maven

- Declear dependencies: spring, hibernates etc...
- Maven will download the JAR files
- And Maven will make those JAR files available during compile /run.

## Application entry point

- @SpringBootApplication is entry point of application.
  - is composed of the following annotations:

|Annotation|Description|
|--|--|
|`@EnableAutoConfiguration` | Enable Spring boot's auto configuration support|
|`@componentScan` | Enables component scanning of current package also recursively scan sub-packages|
|`@Configuration` | Able to register extra beans with @Bean or import other configuration classe|

```java
@SpringBootApplication
public class MyKartApplication {
 public static void main(String[] args) {
  SpringApplication.run(MyKartApplication.class, args);
 }
}
```

`SpringApplication.run` boostrap spring boot application
Scan everything in `com.packageName.myapp` and sub-package

### More on component scanning

Explicitly list base packages to scan
    ```java
    @SpringBootApplication(
        scanBasePackages={"com.packageName.myapp",
        "com.packageName.util",
        "org.acme.cart"
        }
    )
    public class MyKartApplication {
    }
    ```

## Maven - How it works ?

- Project config file (Spring Hibernate Commons logging)
    1. Read config file
    2. Maven check local repo
    3. Maven get from remote repo
    4. Maven save in local repo
- Maven will handle class/build path automatically.

## Project Structure

```cmd
myapp/
├── pom.xml                 # Maven project configuration
├── src/
│   ├── main/
│   │   ├── java/          # Application source code
│   │   │   └── com/
│   │   │       └── coolCode/
│   │   │           ├── controller/    # REST controllers
│   │   │           ├── service/       # Business logic
│   │   │           ├── dao/           # Data access objects
│   │   │           └── entity/        # JPA entities
│   │   ├── resources/    # Configuration files, properties
│   │   │   ├── application.properties
│   │   │   └── application.yml
│   │   └── webapp/       # Web assets (for WAR packaging only)
│   │       └── WEB-INF/
│   └── test/
│       ├── java/         # Unit & integration tests
│       └── resources/    # Test configurations
└── target/              # Compiled output
```

### Directory Overview

| Directory | Purpose |
|-----------|---------|
| `src/main/java` | Java source code with your application logic |
| `src/main/resources` | Configuration files, properties, and static resources |
| `src/main/webapp` | Web assets (JSP, HTML, CSS, JS) - **Only for WAR packaging** |
| `src/test` | Test cases and test resources |
| `target` | Compiled bytecode and built artifacts |
| `pom.xml` | Maven project configuration and dependencies |

### Key Packages

- `controller/` - REST endpoints and MVC controllers
- `service/` - Business logic implementation
- `dao/` - Database operations and repositories
- `entity/` - Domain models and JPA entities

- pom.xml - project object model file
  - Configuration file
        - meta  data
        - dependencies
        - plug ins

    - <https://central.sonatype.com> to find dependency.d

- Maven Wrapper files
  - mvnw allow you to run a maven project
        - No need to have Maven installed
        - If correct version of maven is not found it will automatically donwloads
  - two files are provided.
        - mvnw.cmd for windows
            ```cmd
            > mvnw clean compile test
            ```
        - mvnw.sh for linux
            ```bash
            $ ./mvnw clean compile test
            ```

## Application properties

```properties
# configure server port
server.port=8484

# configure my props
coach.name=mickey
team.name=the mouse
```

```java
@value("${coach.name}")
private String coachName;
```

## Actuator

- Automatically expose endpoints for metric
- Endpoint /actuator

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

### Health Check

- By default, exposed
**GET** `/actuator/health`

```json
{
    "status": "UP"
}
```

### Info Endpoint

**GET** `/actuator/info`

```json
{
    "app": {
        "name": "My app",
        "description": "long description",
        "version": "1.0.0"
    }
}
```

### Example Configuration

```properties
# file: main/resources/application.properties
# Actuator configuration
management.endpoints.web.exposure.include=health,info

management.info.env.enabled=true

# Info endpoint custom properties
info.app.name=My app
info.app.description=long description
info.app.version=1.0.0

# Hide banner
spring.main.banner-mode=off
# Reduce logging
logging.level.root=warn
```

:::tip
Use `management.endpoints.web.exposure.include=*` to expose all endpoints
:::

- /beans
  - List of all spring beans registered with application
- /threaddump
  - list all of threads runing in your application
- /mappings
  - list all of the request mappings for your app. useful for finding out what request mapping are available.

### Security configuration

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

```properties
spring.security.user.name=scott
spring.security.user.password=tiger
```

### Log SQL statements

- Log sql statements
- Log values for sql statements

  ```java
  # Hibernate ddl auto (create, create-drop, validate, update)
  logging.level.org.hibernate.SQL=debug
  logging.level.org.hibernate.orm.jdbc.bind=trace
  ```

## WARNING

Do not use src/main/webapp directory if your application is packaged as a JAR. it is silently ignored by most build tools if you generate a JAR.

:::tip
For Spring Boot JAR applications, avoid using `src/main/webapp`. Use `src/main/resources/static` or `src/main/resources/templates` instead.
:::
