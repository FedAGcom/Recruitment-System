--liquibase formatted sql
--changeset Artem:create-table-skills
CREATE TABLE skills
(
    id         bigserial PRIMARY KEY,
    skill_name varchar(128) NOT NULL
);

