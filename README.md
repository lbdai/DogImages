### Requirements
 - Java 21
 - Maven 3 
 # Running the application locally 
  There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.java.assessment.love.dogs.Application` class from your IDE.
  Alternatively you can use the Spring Boot Maven plugin like so:
  Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

 # API configuration
  ```shell
    address: http://localhost
    port:8080
  ```

 # API Document
  ```shell 
    http://localhost:8080/swagger-ui/index.html
  ```
# check spotless
```shell 
   mvn spotless:check
  ```
 
# apply spotless
```shell 
   mvn spotless:apply
  ```

