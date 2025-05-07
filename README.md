# Full Stack Microservices Project

This project is a Full Stack Microservices application built using Spring Boot for backend services and MySQL for data storage. The current version includes a basic User microservice with a working database connection.

## ✅ Features

- User microservice implemented in Spring Boot
- MySQL database integration via JDBC
- Entity class for `User` created
- Connection tested and verified

---

## 🛠️ Tech Stack

- **Backend**: Spring Boot, Spring Data JPA
- **Database**: MySQL
- **ORM**: Hibernate (JPA)
- **Build Tool**: Maven or Gradle
- **Java Version**: 17 (or your version)

---

## 📁 Project Structure (User Service)

user-service/
├── src/
│ └── main/
│ ├── java/
│ │ └── com.example.userservice/
│ │ ├── entity/
│ │ │ └── User.java
│ │ ├── repository/
│ │ │ └── UserRepository.java
│ │ ├── controller/
│ │ │ └── UserController.java
│ │ └── UserServiceApplication.java
│ └── resources/
│ └── application.properties
├── pom.xml




---

## 📦 How to Run

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-username/fullstack-microservices.git
   cd user-service
   
   
📬 API Endpoints (User Service)
Method	Endpoint	Description
GET	    /users	    Get all users
POST	/users	    Create new user
GET	    /users/{id}	Get user by ID

You can test these using Postman or any REST client.
