# Full Stack Microservices Project
This project is a Full Stack Microservices application built using Spring Boot for backend services. The current version includes a basic User microservice and Activity microservice with a working database connection.
## ✅ Features

- User and Activity microservice implemented in Spring Boot
- MySQL database integration via JDBC
- Entity class for `User` and `Activity` created
- Connection tested and verified
---
## 🛠️ Tech Stack

- **Backend**: Spring Boot, Spring Data JPA , Spring Data Mongodb
- **Database**: MySQL,MongoDB
- **ORM**: Hibernate (JPA)
- **Build Tool**: Maven
- **Java Version**: 17 (or your version)

---
 
   
📬 API Endpoints (User Service)

## Base URL:
   - http://localhost:8080/api/users

| Method   | Endpoint    | Description                  | Request Body     | Response       |
| -------- |-------------|------------------------------| ---------------- | -------------- |
| `GET`    | `/{id}`     | Get user by ID / userProfile | —                | 200 OK / 404   |
| `POST`   | `/register` | Create a new user            | JSON user object | 201 Created    |

