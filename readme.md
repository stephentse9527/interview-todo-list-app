Interview-Todo list app 
=============

This project is the implementaion of project interview-todo-list-app-and-keepie



Introduction
------------

This project utilizes Java as the development language, Spring Boot as the primary framework, and Spring Security for authentication. Maven is used for project management, with Sqlite serving as a lightweight embedded database.

### Environment Requirements

To successfully run this project, ensure the following prerequisites are met:

- Java 8 or higher
- Maven 
- Sqlite3

### How to run this project in local

1.Please modify the setting in application.yml file:

```yaml
# local keepie url as the default keepie url
keepie:
  url: "http://localhost:8000"

# local url as the default receive_url
receive-url: "http://localhost:8080"

server:
  port: 8080
  address: 0.0.0.0

spring:
  datasource:
    driver-class-name: org.sqlite.JDBC
    # sqlite jdbc url patten: jdbc:sqlite:your path, update this path as your local db file location
    url: jdbc:sqlite:your local db file location
    # for example - url: jdbc:sqlite:/opt/todolist/task.db
```



2.cd to the root directory of the project, then executing the following command:

```sh
mvn clean package
```

3.run the project:

```sh
java -jar ./target/todolist-1.0-SNAPSHOT.jar
```

4.**Start Keepie Service**: As this project relies on the Keepie service, ensure that a local or internet instance of the Keepie service is running

Usage
-----

Curl endpoints:

1.To access other APIs securely, you first need to obtain credentials from the Keepie service using the provided endpoint. Here's how you can do it:

```shell
curl -X GET http://localhost:8080/notify_keepie
# Update user credential successfully
```

The output of the above script will be like below and return http code 200:

```sh
Update user credential successfully
```

2.You can also explore and test the APIs using Swagger UI by accessing below link

``http://localhost:8080/swagger-ui.html#/``

3.For all task-related APIs, the application will only process and execute requests if valid credentials are provided.

# How it works

1. By triggering the `/notify_keepie` endpoint to prompt the Keepie service to send credentials. The project simultaneously listens on this endpoint. Once it receives a POST request from Keepie, it will store the user credentials to memory.
2. Using SpringSecurity to enforce authentication. Only requests with valid authentication details are allowed to proceed and be processed.