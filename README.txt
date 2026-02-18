CAR CRUD APP

**************** Stack ****************
- Java 17
- Spring Boot 3.x
- Spring Data JPA (Hibernate)
- PostgreSQL (or H2 for in-memory testing)
- ModelMapper for DTO mapping
- Maven for dependency management
- Postman for API testing



**************** Features ****************
- CRUD operations for Car entities (Create, Read, Update, Delete)
- Entity → DTO mapping using ModelMapper
- Input validation using @Valid and Hibernate Validator annotations
- Global exception handling (@RestControllerAdvice) for 400 and 500 errors
- RESTful API design with proper HTTP status codes



**************** Project Structure ****************
Packages:
- controller => REST controllers
- dto	     => Data Transfer Objects
- exception  => Global exception handler + custom exceptions
- model      => JPA entities
- repo 	     => JPA repositories
- service    => Business Logic

- Application class
- Application properties



**************** API Endpoints ****************
| Method | Endpoint | Description |

| GET | /cars | Get all cars |

| GET | /cars/{id} | Get car by id |

| POST | /cars | Add car |

| PUT | /cars/{id} | Update car information |

| DELETE | /cars{id} | Delete car|



**************** Request & Response Example (Postman) ****************
Request: 
POST -> http://localhost:9090/cars
JSON Body
{
    "make":"Ford",
    "model":"Raptor",
    "type":"Author1"
}

Response: 201 Created
JSON
{
    "id": 1,
    "make": "Ford",
    "model": "Raptor",
    "type": "Author1"
}



**************** How to Run the Project ****************
Clone the repository:
git clone <your-repo-url>
cd CRUDCarApp


Configure the database in application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/cardb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


Build and run:
mvn clean install
mvn spring-boot:run


The API will run at:
http://localhost:8080/cars



**************** Validation & Error Handling ****************
400 Bad Request

Request: http://localhost:8080/cars
{
    "model": "",
    "make": "q"
}

Response: 400 Bad Request
{
    "model": "must contain at least 2 characters with only letters, numbers, and spaces",
    "type": "type is required",
    "make": "must contain at least 3 characters with only letters and spaces"
}

500 Internal Server Errors

Request: http://localhost:8080/cars/754
{
    "model": "GT40",
    "type": "Supercar",
    "make": "Ford"
}

Response: 500 Internal Server Error
{
    "details": "Car not found",
    "error": "Internal server error"
}