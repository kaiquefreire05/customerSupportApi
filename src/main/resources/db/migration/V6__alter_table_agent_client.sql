ALTER TABLE clients
    ADD UNIQUE (Email);
ALTER TABLE agents
    ADD UNIQUE (Email);
