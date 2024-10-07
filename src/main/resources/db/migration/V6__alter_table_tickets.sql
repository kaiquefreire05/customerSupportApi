alter table supporttickets
    change ClientId Client_Id char(36) null;

alter table supporttickets
    change AgentId Agent_Id char(36) null;