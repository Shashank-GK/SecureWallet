# 🚀 Secure Wallet - Encrypter & Decrypter  

**Secure Wallet** is a **Spring Boot 3+** application designed for securely storing, encrypting, and retrieving user credentials. It provides **AES encryption** for sensitive data and follows a structured design pattern with controllers, services, repositories, utilities, and exception handling.

---

## 🛠 Project Overview  

- **Secure Credential Storage**: Encrypts and stores user credentials safely.  
- **Encryption & Decryption**: Uses AES encryption to protect sensitive data.  
- **Exception Handling**: Global handling of application errors.  
- **Swagger API Documentation**: Provides an interactive UI for testing APIs.  
- **Repository Pattern**: Ensures clean separation of data persistence logic.  
- **Docker Support**: Easily deployable via Docker.  

---

## 🔧 Tech Stack Used  

| Technology    | Description |
|--------------|------------|
| **Java 17**  | Core programming language |
| **Spring Boot 3+** | Backend framework for building REST APIs |
| **Cassandra** | NoSQL database for credential storage |
| **Spring Data Cassandra** | ORM for interacting with Cassandra |
| **Lombok** | Reduces boilerplate code |
| **Jakarta Validation** | Validates API request data |
| **Swagger OpenAPI** | API documentation and testing |
| **Docker** | Containerization for easy deployment |
| **Maven** | Dependency management |

---

## 🏗 Project Structure  

```
IVO-ENCRYPTER-DECRYPTER/
├── src/
│   ├── main/
│   │   ├── java/com/ivoyant/secure_wallet/
│   │   │   ├── config/               # Configuration files (Swagger, Security, etc.)
│   │   │   │   ├── SwaggerConfig.java
│   │   │   ├── constants/             # Application-wide constants
│   │   │   │   ├── StringConstants.java
│   │   │   ├── controller/            # REST controllers for handling API requests
│   │   │   │   ├── SecureWalletController.java
│   │   │   ├── dto/                   # Data Transfer Objects for requests and responses
│   │   │   ├── entity/                # Entity classes for Cassandra database
│   │   │   ├── exception/             # Custom exceptions and global handlers
│   │   │   │   ├── CustomExceptionObject.java
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── TransactionIncompleteException.java
│   │   │   ├── repository/            # Repository layer for database operations
│   │   │   │   ├── impl/              # Repository implementation using CassandraTemplate
│   │   │   │   │   ├── SecureWalletRepository.java
│   │   │   ├── service/               # Business logic implementation
│   │   │   ├── utility/               # Encryption/Decryption utilities
│   │   │   │   ├── EncryptDecryptUtil.java
│   │   │   ├── SecureWalletApplication.java  # Main entry point of the application
│   ├── resources/                      # Application configuration files
│   ├── test/                            # Unit and integration tests
├── target/                              # Compiled output files
├── .gitignore                           # Files to ignore in version control
├── Dockerfile                           # Docker configuration for containerization
├── HELP.md                              # Additional help documentation
├── pom.xml                              # Maven dependencies and build configuration
```

---

## 📌 How to Use the Project  

### 1️⃣ **Prerequisites**  

Make sure you have the following installed:  

- **Java 17+**  
- **Apache Maven**  
- **Cassandra Database**  
- **Docker (Optional for containerization)**  

---

### 2️⃣ **Installation & Setup**  

1️⃣ **Clone the Repository**  
```sh
git clone https://github.com/your-repo/SecureWallet.git
cd SecureWallet
```

2️⃣ **Build the Project**  
```sh
mvn clean install
```

3️⃣ **Run the Application**  
```sh
mvn spring-boot:run
```

---

### 3️⃣ **API Endpoints**  

📌 **Base URL**: `http://localhost:8080`  

| Method | Endpoint | Description |
|--------|---------|------------|
| **POST** | `/v1/user-credentials/add` | Add a new encrypted user credential |
| **GET** | `/v1/user-credentials/search/{userUuid}/{title}` | Retrieve a decrypted user credential |
| **GET** | `/v1/user-credentials/list` | Fetch all stored credentials |

---

### 4️⃣ **Swagger API Documentation**  

After running the application, open **Swagger UI** to test APIs:  

🔗 [Swagger UI](http://localhost:8080/swagger-ui/index.html)  

---

## 🔐 Security Considerations  

- **DO NOT** commit sensitive data (e.g., database credentials) in `application.properties`.  
- Use **environment variables** or **Spring Config Server** for managing secrets securely.  

--- 

Feel free to submit **issues** or **pull requests** to improve the project.  

📧 **Contact:** [your-email@example.com](mailto:your-email@example.com)  

---

**© 2025 Secure Wallet - All Rights Reserved.**
