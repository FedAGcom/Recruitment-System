--liquibase formatted sql
--changeset Nick:create-table-education
CREATE TABLE education
(
    id bigserial PRIMARY KEY,
    user_id bigint NOT NULL,
    name varchar(128) NOT NULL,
    faculty varchar(128),
    specialty varchar(128),
    age int NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);