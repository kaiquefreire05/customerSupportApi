ALTER TABLE feedbacks
    RENAME COLUMN SupportTicketId TO Support_Ticket_Id;

ALTER TABLE feedbacks
    ALTER COLUMN Support_Ticket_Id TYPE BIGINT;