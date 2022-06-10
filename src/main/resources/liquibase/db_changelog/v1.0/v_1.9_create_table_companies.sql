--liquibase formatted sql
--changeset Artem:create-table-companies
CREATE TABLE companies
(
    id bigserial PRIMARY KEY,
    company_name varchar(256) UNIQUE NOT NULL,
    email varchar(128) UNIQUE NOT NULL,
    location varchar(128) NOT NULL,
    role enum_role_type DEFAULT 'USER' NOT NULL,
    password varchar(128)
);

