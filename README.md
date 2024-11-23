# Customer Support API

## Description

This project is a customer support API built with Spring Boot. The API allows the creation, reading, updating, and deletion of customer support tickets.

## Requirements

- Java 17 or higher
- Maven 3.8.1 or higher
- PostgreSQL 13 or higher

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/customer-support-api.git
    cd customer-support-api
    ```

2. Set up the PostgreSQL database:
    ```sql
    CREATE DATABASE customer_support;
    CREATE USER user WITH ENCRYPTED PASSWORD 'password';
    GRANT ALL PRIVILEGES ON DATABASE customer_support TO user;
    ```

3. Configure the `application.properties` file:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/customer_support
    spring.datasource.username=user
    spring.datasource.password=password
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Build and run the project:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

## API Endpoints

### Authentication

- **POST /auth/login**
    - Description: Authenticates a user and returns a JWT token.
    - Request Body:
        ```json
        {
            "username": "string",
            "password": "string"
        }
        ```
    - Response Body:
        ```json
        {
            "token": "string"
        }
        ```

### Tickets

- **GET /tickets**
    - Description: Returns a list of all tickets.
    - Response Body:
        ```json
        [
            {
                "id": "integer",
                "title": "string",
                "description": "string",
                "status": "string",
                "createdAt": "datetime",
                "updatedAt": "datetime"
            }
        ]
        ```

- **GET /tickets/{id}**
    - Description: Returns a specific ticket by ID.
    - Response Body:
        ```json
        {
            "id": "integer",
            "title": "string",
            "description": "string",
            "status": "string",
            "createdAt": "datetime",
            "updatedAt": "datetime"
        }
        ```

- **POST /tickets**
    - Description: Creates a new ticket.
    - Request Body:
        ```json
        {
            "title": "string",
            "description": "string"
        }
        ```
    - Response Body:
        ```json
        {
            "id": "integer",
            "title": "string",
            "description": "string",
            "status": "string",
            "createdAt": "datetime",
            "updatedAt": "datetime"
        }
        ```

- **PUT /tickets/{id}**
    - Description: Updates an existing ticket.
    - Request Body:
        ```json
        {
            "title": "string",
            "description": "string",
            "status": "string"
        }
        ```
    - Response Body:
        ```json
        {
            "id": "integer",
            "title": "string",
            "description": "string",
            "status": "string",
            "createdAt": "datetime",
            "updatedAt": "datetime"
        }
        ```

- **DELETE /tickets/{id}**
    - Description: Deletes a ticket by ID.
    - Response Body:
        ```json
        {
            "message": "Ticket deleted successfully"
        }
        ```

## Contribution

1. Fork the project.
2. Create a new branch:
    ```sh
    git checkout -b feature/new-feature
    ```
3. Make your changes and commit:
    ```sh
    git commit -m 'Add new feature'
    ```
4. Push to the remote repository:
    ```sh
    git push origin feature/new-feature
    ```
5. Open a Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.