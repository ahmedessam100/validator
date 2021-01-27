# Validator back-end

## To run the project

- Install Maven
  
- Build and test Spring Boot Project with Maven ```mvn clean install```

- Run Spring Boot app using Maven ```mvn spring-boot:run``` 

## To dockerize the project:

- ```docker build -t validator-be/gs-spring-boot-docker .```

- ```docker run -p 8080:8080 validator-be/gs-spring-boot-docker```

- The server is running on port 8080