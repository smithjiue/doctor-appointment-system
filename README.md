# Doctors Appointment System

A Spring Boot REST API for managing doctors, patients, and appointments. This system provides a complete solution for healthcare appointment management with proper validation, error handling, and API documentation.

## 🚀 Features

- **Doctor Management**: CRUD operations for doctors with specialization tracking
- **Patient Management**: CRUD operations for patients with contact information
- **Appointment Management**: Schedule and manage appointments between doctors and patients
- **RESTful API**: Well-structured REST endpoints with proper HTTP methods
- **Data Validation**: Input validation using Bean Validation
- **Error Handling**: Global exception handling with meaningful error messages
- **API Documentation**: Swagger/OpenAPI documentation
- **Database**: MySQL with JPA/Hibernate
- **Docker Support**: Containerized application with Docker Compose

## 🛠️ Technology Stack

- **Java**: 24
- **Spring Boot**: 3.5.4
- **Spring Data JPA**: For database operations
- **MySQL**: Database
- **Lombok**: For reducing boilerplate code
- **Swagger/OpenAPI**: API documentation
- **Maven**: Build tool
- **Docker**: Containerization

## 📋 Prerequisites

- Java 24 or higher
- Maven 3.6+
- MySQL 8.4+ (or Docker for containerized setup)
- Git

## 🚀 Setup Instructions

### Option 1: Local Development Setup

1. **Clone the repository**

   ```bash
   git clone <https://github.com/smithjiue/doctor-appointment-system.git>
   cd doctors_appointment_system
   ```

2. **Set up MySQL Database**

   - Install MySQL 8.4+
   - Create a database named `doctors_dev_db`
   - Update database credentials in `src/main/resources/application-dev.properties` if needed

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

### Option 2: Docker Setup (Recommended)

1. **Clone the repository**

   ```bash
   git clone <https://github.com/smithjiue/doctor-appointment-system.git>
   cd doctors_appointment_system
   ```

2. **Start the application with Docker Compose**
   ```bash
   docker-compose up -d
   ```

This will start both the MySQL database and the Spring Boot application.

## 🏃‍♂️ Run Commands

### Development Mode

```bash
mvn spring-boot:run
```

### Production Mode

```bash
mvn clean package
java -jar target/doctors_appointment_system-0.0.1-SNAPSHOT.jar
```

### Docker Mode

```bash
# Start all services
docker-compose up -d

# Stop all services
docker-compose down

# View logs
docker-compose logs -f
```

## 📚 API Documentation

Once the application is running, you can access the Swagger UI at:

- **Local**: http://localhost:8080/swagger-ui.html
- **Docker**: http://localhost:8080/swagger-ui.html

## 🔌 API Endpoints

### Base URL

```
http://localhost:8080/api/v1
```

### 1. Doctors API

#### Create a Doctor

```http
POST /doctors
Content-Type: application/json

{
  "name": "Dr. John Smith",
  "specialization": "Cardiology",
  "email": "john.smith@hospital.com",
  "phone": "+1-555-0123"
}
```

#### Get All Doctors (with pagination)

```http
GET /doctors?page=0&size=10&sort=name,asc
```

#### Get Doctor by ID

```http
GET /doctors/{id}
```

#### Update Doctor

```http
PUT /doctors/{id}
Content-Type: application/json

{
  "name": "Dr. John Smith",
  "specialization": "Cardiology",
  "email": "john.smith@hospital.com",
  "phone": "+1-555-0123"
}
```

#### Delete Doctor

```http
DELETE /doctors/{id}
```

### 2. Patients API

#### Create a Patient

```http
POST /patients
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane.doe@email.com",
  "phone": "+1-555-0456"
}
```

#### Get All Patients (with pagination)

```http
GET /patients?page=0&size=20&sort=name,asc
```

#### Get Patient by ID

```http
GET /patients/{id}
```

#### Update Patient

```http
PUT /patients/{id}
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane.doe@email.com",
  "phone": "+1-555-0456"
}
```

#### Delete Patient

```http
DELETE /patients/{id}
```

### 3. Appointments API

#### Create an Appointment

```http
POST /appointments
Content-Type: application/json

{
  "doctorId": 1,
  "patientId": 1,
  "appointmentDateTime": "2024-01-15T10:00:00"
}
```

#### Get All Appointments (with filtering)

```http
GET /appointments?doctorId=1&patientId=1&page=0&size=20&sort=appointmentDateTime,asc
```

#### Get Appointment by ID

```http
GET /appointments/{id}
```

#### Delete Appointment

```http
DELETE /appointments/{id}
```

## 📊 Database Schema

### Doctor Table

```sql
CREATE TABLE doctor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone VARCHAR(20)
);
```

### Patient Table

```sql
CREATE TABLE patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone VARCHAR(20)
);
```

### Appointment Table

```sql
CREATE TABLE appointment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    appointment_date_time DATETIME NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    UNIQUE KEY idx_appt_doctor_time (doctor_id, appointment_date_time)
);
```

## 🗄️ Database Dump

The `doctor_db.sql` file contains the complete database schema and sample data. You can import it using:

```bash
mysql -u root -p doctors_dev_db < doctor_db.sql
```

## 🔧 Configuration

### Application Properties

#### Development (`application-dev.properties`)

```properties
spring.debug=true
spring.datasource.url=jdbc:mysql://localhost:3307/doctors_dev_db
spring.datasource.username=root
spring.datasource.password=password
logging.level.org.springframework.web=DEBUG
```

#### Production (`application-prod.properties`)

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
server.port=${PORT:8080}
```

## 🧪 Testing

Run the test suite:

```bash
mvn test
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/das/doctors_appointment_system/
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── exception/      # Exception handling
│   │   ├── model/          # JPA entities
│   │   ├── repository/     # Data access layer
│   │   └── service/        # Business logic
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       └── application-prod.properties
└── test/                   # Test classes
```

## 🐛 Error Handling

The application includes comprehensive error handling:

- **400 Bad Request**: Invalid input data
- **404 Not Found**: Resource not found
- **409 Conflict**: Email already exists
- **500 Internal Server Error**: Server-side errors

## 📝 Validation Rules

### Doctor

- Name: Required, max 100 characters
- Specialization: Required, max 100 characters
- Email: Required, valid email format, max 150 characters, unique
- Phone: Optional, max 20 characters

### Patient

- Name: Required, max 100 characters
- Email: Required, valid email format, max 150 characters, unique
- Phone: Optional, max 20 characters

### Appointment

- Doctor ID: Required
- Patient ID: Required
- Appointment DateTime: Required, unique per doctor

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 👥 Authors

- Assignment for Qube Health

## 🆘 Support

For support and questions, please open an issue in the repository.
