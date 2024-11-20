CREATE TABLE Feedbacks
(
    Id              BIGINT PRIMARY KEY,
    Comments        TEXT      NOT NULL,
    Rating          INT       NOT NULL,
    Created_At      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Updated_At      TIMESTAMP,
    SupportTicketId BIGINT,

    FOREIGN KEY (SupportTicketId) REFERENCES SupportTickets (Id) ON DELETE CASCADE
);