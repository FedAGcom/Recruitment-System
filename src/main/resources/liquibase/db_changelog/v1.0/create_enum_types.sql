--liquibase formatted sql
--changeset Artem:create-enum-types
CREATE TYPE enum_user_company_type AS ENUM('USER', 'COMPANY');
CREATE TYPE enum_resume_vacancy_status_type AS ENUM('ACTIVE', 'INACTIVE');
CREATE TYPE enum_vacancy_responses_status_type AS ENUM ('INVITE', 'REFUSAL', 'VIEWED','NOT_VIEWED');
CREATE TYPE enum_messages_is_read_type  AS ENUM ('READ','NO_READ');
CREATE TYPE enum_role_type AS ENUM('USER', 'ADMIN', 'USER_INACTIVE', 'ADMIN_INACTIVE',
    'COMPANY', 'COMPANY_INACTIVE');
CREATE TYPE enum_active_status_type AS ENUM('ACTIVE', 'INACTIVE');

CREATE CAST (character varying as enum_user_company_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_resume_vacancy_status_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_vacancy_responses_status_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_messages_is_read_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_role_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_active_status_type) WITH INOUT AS IMPLICIT;
