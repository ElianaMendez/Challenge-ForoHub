# ForoHub - Challenge Back-End Oracle Alura LATAM

**ForoHub** es una API RESTful desarrollada en Java con Spring Boot, como parte del challenge del programa de formación Back-End con Oracle & Alura LATAM. La aplicación simula un foro académico donde los usuarios pueden crear, consultar, actualizar y eliminar tópicos relacionados con cursos, así como autenticar usuarios mediante JWT.

## 🚀 Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Security con JWT**
- **Maven**
- **Flyway** (migraciones en base de datos)
- **PostgreSQL** 
- **JPA & Hibernate**
- **Lombok**
- **Postman** (para pruebas de endpoints)

---

## 🗂️ Estructura del proyecto

```plaintext
src/main/java/com/challenge/forohub/
│
├── controller/
│   ├── AuthenticationController.java
│   └── TopicController.java
│
├── domain/
│   ├── answer/
│   │   ├── Answer.java
│   │   ├── AnswerRepository.java
│   │   └── AnswerResponseDTO.java
│   │
│   ├── course/
│   │   ├── Category.java
│   │   ├── Course.java
│   │   └── CourseRepository.java
│   │
│   ├── topic/
│   │   ├── Topic.java
│   │   ├── TopicDetailUpdatedDTO.java
│   │   ├── TopicIdResponseDTO.java
│   │   ├── TopicRepository.java
│   │   ├── TopicRequestDTO.java
│   │   ├── TopicResponseDTO.java
│   │   ├── TopicService.java
│   │   ├── TopicStatus.java
│   │   └── TopicUpdateDTO.java
│   │
│   └── user/
│       ├── Profile.java
│       ├── User.java
│       ├── UserAuthenticationDTO.java
│       └── UserRepository.java
│
├── infra/security/
│   ├── AuthenticationService.java
│   ├── BCryptUtils.java
│   ├── DataJWTTokenDTO.java
│   ├── SecurityConfigurations.java
│   ├── SecurityFilter.java
│   └── TokenService.java
│
└── ForohubApplication.java

```

🗃️ Migraciones con Flyway
Ubicadas en resources/db.migration, las migraciones crean y poblan las tablas de usuarios, cursos, tópicos y respuestas.

```plaintext
resources/
└── db.migration/
    ├── V1__Create-Table-Users.sql
    ├── V2__Insert-Into-Table-Users.sql
    ├── V3__create_courses_table.sql
    ├── V4__insert_into_courses_table.sql
    ├── V5__fix_insert_into_courses_table.sql
    ├── V6__create_table_topics.sql
    ├── V7__create_answers_table.sql
    ├── V8__insert_into_new_users.sql
    └── V9__insert_into_answers.sql
```

🔐 Autenticación
La aplicación utiliza JWT (JSON Web Token) para autenticar a los usuarios.

Endpoint: POST /login

Request JSON:

```plaintext
{
  "login": "ana-martinez@forohub.com",
  "password": "123456"
}
```

Response:

```plaintext
{
  "jwtToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

Este token debe ser incluido en el header Authorization para consumir los endpoints protegidos:

Authorization: Bearer <token>

📚 Endpoints

🔸 Crear un nuevo tópico
POST /topic

Body JSON:

```plaintext
{
  "title": "Managing test data in CI/CD pipelines",
  "message": "How do you manage test datasets when using GitHub Actions or Jenkins?",
  "status": "OPEN",
  "userId": 4,
  "courseId": 2
}
```

Response:

```plaintext
{
    "id": 11,
    "title": "Managing test data in CI/CD pipelines",
    "message": "How do you manage test datasets when using GitHub Actions or Jenkins?",
    "creationDate": "2025-08-08T12:52:33.5621958",
    "status": "OPEN",
    "user": "Pedro Torres",
    "course": "Web Development with JavaScript"
}
```

🔹 Listar todos los tópicos
GET /topic

Response:

```plaintext
[
    {
        "id": 1,
        "title": "JUnit Assertions best practices",
        "message": "What are the most recommended assertions to use in JUnit?",
        "creationDate": "2025-08-06T11:28:33.062408",
        "status": "OPEN",
        "user": "Carlos Gómez",
        "course": "Java Fundamentals"
    },
    {
        "id": 2,
        "title": "How to mock services in unit tests",
        "message": "Is Mockito enough to mock third-party services during tests?",
        "creationDate": "2025-08-06T11:28:57.170167",
        "status": "OPEN",
        "user": "Laura Ruiz",
        "course": "Web Development with JavaScript"
    }
] ...
```

🔸 Obtener detalle de tópico por ID (con respuestas)
GET /topic/{id}

Response:
```plaintext
{
  "id": 1,
  "title": "Updated title",
  "message": "Updated message",
  "creationDate": "2025-08-06T10:37:04.907401",
  "status": "OPEN",
  "userName": "Ana Martínez",
  "courseName": "Automated Testing with Selenium",
  "answers": [
    {
      "id": 1,
      "message": "You can use Selenium with JUnit to run full browser tests efficiently.",
      "creationDate": "2025-08-06T21:27:28.752374",
      "user": "Carlos Gómez",
      "solution": false
    },
    {
      "id": 2,
      "message": "Headless mode helps you run tests in CI/CD pipelines.",
      "creationDate": "2025-08-06T21:29:11.845317",
      "user": "Luis Torres",
      "solution": true
    }
  ]
}
```

🔹 Actualizar un tópico
PUT /topic/{id}

Body JSON:
```plaintext
{
  "title": "Updated title",
  "message": "Updated message"
}
```
🔸 Eliminar un tópico
DELETE /topic/{id}

Valida si el ID existe previamente con Optional.isPresent() antes de eliminar con deleteById().

👤 Autor
Eliana Méndez – QA Engineer y Java Developer

*Este proyecto fue desarrollado por Eliana Méndez como parte del programa Oracle Next Education (ONE) con Alura LATAM.





