# Course Management Backend

A RESTful API built with **Spring Boot 3**, **Spring Data JPA**, and **H2 in-memory database**.

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+

### Run the Application
```bash
./mvnw spring-boot:run
```

The server starts at: `http://localhost:8080`

H2 Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:coursedb`
- Username: `sa`
- Password: *(leave blank)*

---

## 📡 API Endpoints

| Method | Endpoint                              | Description              |
|--------|---------------------------------------|--------------------------|
| GET    | `/api/courses`                        | Get all courses          |
| GET    | `/api/courses/{id}`                   | Get course by ID         |
| POST   | `/api/courses`                        | Create a new course      |
| PUT    | `/api/courses/{id}`                   | Update a course          |
| DELETE | `/api/courses/{id}`                   | Delete a course          |
| GET    | `/api/courses/search?keyword=`        | Search courses           |
| GET    | `/api/courses/category/{category}`    | Filter by category       |
| GET    | `/api/courses/active`                 | Get active courses       |
| GET    | `/api/courses/price-range?minPrice=&maxPrice=` | Filter by price |
| POST   | `/api/courses/{id}/enroll`            | Enroll a student         |

---

## 📦 Sample Request Body (POST/PUT)

```json
{
  "title": "Java Spring Boot Masterclass",
  "description": "Complete guide to building REST APIs with Spring Boot 3",
  "instructor": "John Smith",
  "durationHours": 40,
  "price": 99.99,
  "category": "Backend",
  "level": "INTERMEDIATE",
  "isActive": true
}
```

---

## 🏗️ Project Structure

```
src/main/java/com/coursemanagement/
├── CourseManagementApplication.java
├── controller/CourseController.java
├── service/CourseService.java
├── repository/CourseRepository.java
├── entity/Course.java
├── dto/CourseDTO.java
├── config/CorsConfig.java
└── exception/
    ├── ResourceNotFoundException.java
    └── GlobalExceptionHandler.java
```

---

## 🛠️ Tech Stack

- **Spring Boot 3.2**
- **Spring Data JPA**
- **H2 In-Memory Database**
- **Lombok**
- **Bean Validation**
"# course-management-backend" 
"# course-management-backend" 
