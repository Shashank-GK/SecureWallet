# SecureWallet


# Secure Wallet

Secure Wallet is a Spring Boot application designed to encrypt and store sensitive user data securely. This application follows a structured design pattern, incorporating controllers, services, repositories, utilities, and configurations.

## Features
- Secure credential storage
- Encryption and decryption utilities
- API documentation using Swagger
- Exception handling
- Repository pattern for data persistence

## Project Structure
```
secure_wallet/
├── src/
│   ├── main/
│   │   ├── java/com/ivoyant/secure_wallet/
│   │   │   ├── config/
│   │   │   │   ├── SwaggerConfig.java
│   │   │   ├── constants/
│   │   │   │   ├── StringConstants.java
│   │   │   ├── controller/
│   │   │   │   ├── SecureWalletController.java
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   │   ├── impl/
│   │   │   │   │   ├── SecureWalletRepository.java
│   │   │   ├── service/
│   │   │   │   ├── SecureWalletService.java
│   │   │   ├── utility/
│   │   │   │   ├── EncryptDecryptUtil.java
│   │   │   ├── SecureWalletApplication.java
│   │   ├── resources/
│   │   │   ├── static/
│   │   │   ├── templates/
│   │   │   ├── application.properties
│   │   │   ├── sql.properties
│   ├── test/
```

## Prerequisites
- Java 17+
- Spring Boot 3+
- Maven
- Cassandra database

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/SecureWallet.git
   ```
2. Navigate to the project directory:
   ```sh
   cd SecureWallet
   ```
3. Build the project:
   ```sh
   mvn clean install
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## API Documentation
Swagger documentation is available at:
```
http://localhost:8080/swagger-ui/index.html
```

## Security Considerations
- **DO NOT** commit sensitive data in `application.properties`.
- Use environment variables for database credentials.
