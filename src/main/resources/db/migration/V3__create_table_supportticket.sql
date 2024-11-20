CREATE TABLE SupportTickets
(
    Id          BIGINT PRIMARY KEY,
    Title       VARCHAR(255) NOT NULL,
    Description TEXT         NOT NULL,
    Status      VARCHAR(20)  NOT NULL,
    Category    VARCHAR(20)  NOT NULL,
    Closed_At   TIMESTAMP,
    Created_At  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Updated_At  TIMESTAMP,
    Client_Id UUID NOT NULL,
    Agent_Id UUID,

    FOREIGN KEY (Client_Id) REFERENCES Clients (Id),
    FOREIGN KEY (Agent_Id) REFERENCES Agents (Id)
);