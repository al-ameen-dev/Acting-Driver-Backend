# Acting Driver Web Application - Backend API

This repository contains the backend REST API for the **Acting Driver** web application. This application connects clients who own vehicles but do not drive, with professional acting drivers who provide driving services on-demand.

---

## Table of Contents

- [About](#about)  
- [Features](#features)  
- [Technologies Used](#technologies-used)  
- [API Endpoints](#api-endpoints)  
- [Getting Started](#getting-started)
- [Project Setup](#project-setup)

---

## About

The Acting Driver backend provides RESTful APIs for managing clients, acting drivers, bookings, reviews, and payments. It handles core business logic, data persistence, and exposes endpoints for frontend or mobile clients to consume.

---

## Features

- User registration and authentication for clients and acting drivers  
- Profile creation, update, and management  
- Booking creation, update, cancellation, and tracking  
- Review and rating system for acting drivers  
- Payment status management and tracking  
- Secure data validation and error handling   

---

## Technologies Used

- Java 17  
- Spring Boot  
- Spring Data JPA / Hibernate  
- Spring Security 
- MySQL / PostgreSQL (or any preferred relational database)  
- Maven or Gradle build tools  
- Lombok for boilerplate code reduction  
- JUnit and Mockito for testing  

---

## API Endpoints

| Resource           | HTTP Methods       | Description                              |
|--------------------|--------------------|------------------------------------------|
| `/api/clients`     | GET, POST, PUT, DELETE | Manage client profiles                    |
| `/api/drivers`     | GET, POST, PUT, DELETE | Manage acting driver profiles             |
| `/api/bookings`    | GET, POST, PUT, DELETE | Create and manage bookings                |
| `/api/reviews`     | GET, POST           | Add and view driver reviews               |
| `/api/payments`    | GET, POST, PUT      | Handle payment status and records         |
| `/api/auth`        | POST                | Authentication and authorization endpoints |

*Detailed API documentation and request/response examples can be added separately.*

---

## Getting Started

### Prerequisites

- Java 17 or higher installed  
- Maven or Gradle build tool installed  
- MySQL/PostgreSQL or other relational database  
- IDE like IntelliJ IDEA, Eclipse, or VS Code  
- API testing tool like Postman (recommended)  


## Project Setup

To set up the Acting Driver backend application, ensure you have Java 17 or later and Maven installed on your system. Start by cloning the repository from GitHub using the command `git clone https://github.com/yourusername/acting-driver-backend.git`, then navigate into the project directory with `cd acting-driver-backend`. Before running the application, configure the database settings in `src/main/resources/application.properties` by specifying the `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`, and optionally `spring.jpa.hibernate.ddl-auto`. Once the configurations are complete, build the project using Maven with `mvn clean install` . You can then run the application using `mvn spring-boot:run` or by executing the generated JAR file with `java -jar target/acting-driver-backend.jar`. By default, the backend REST APIs will be available at `http://localhost:8080/`.
