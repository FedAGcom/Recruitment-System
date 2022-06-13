--liquibase formatted sql
--changeset Artem:create-table-users
CREATE TABLE users
(
    id bigserial PRIMARY KEY,
    first_name varchar(128) NOT NULL,
    last_name varchar(128) NOT NULL,
    email varchar(128) UNIQUE NOT NULL,
    birthday date NOT NULL,
    role enum_role_type DEFAULT 'USER' NOT NULL,
    password varchar(128),
    activation_code varchar(60),
    active_status enum_active_status_type DEFAULT 'ACTIVE' NOT NULL
);

