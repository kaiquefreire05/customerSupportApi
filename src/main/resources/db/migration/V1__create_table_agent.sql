CREATE TABLE Agents
(
    Id UUID NOT NULL PRIMARY KEY,
    Name       VARCHAR(255)        NOT NULL,
    Email      VARCHAR(255) UNIQUE NOT NULL,
    Phone      VARCHAR(20)         NOT NULL,
    Password   VARCHAR(255)        NOT NULL,
    Created_At TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Updated_At TIMESTAMP
);