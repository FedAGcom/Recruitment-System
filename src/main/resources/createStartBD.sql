CREATE DATABASE recruitment_system_test;

-- ENUM в таблицу users в столбец role
-- и в таблице companies в стобец role
CREATE TYPE enum_role_type AS ENUM('USER', 'ADMIN');

CREATE TABLE users
(
    id bigserial PRIMARY KEY,
    first_name varchar(128) NOT NULL,
    last_name varchar(128) NOT NULL,
    email varchar(128) UNIQUE NOT NULL,
    birthday date NOT NULL,
    role enum_role_type DEFAULT 'USER' NOT NULL,
    password varchar(128) NOT NULL,
    activation_code varchar(60)
);

CREATE TABLE entrance_exams
(
    id bigserial PRIMARY KEY,
    user_id bigint NOT NULL,
    score smallint CHECK (score>=0 AND score<=100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- ENUM в таблицу user_feedback в столбец entity_from_type
-- и в таблице messages в стобец from_entity_message
CREATE TYPE enum_user_company_type AS ENUM('USER', 'COMPANY');

CREATE TABLE user_feedback
(
    id bigserial PRIMARY KEY,
    user_to_id bigint NOT NULL,
    entity_from_id bigint NOT NULL,
    entity_from_type enum_user_company_type NOT NULL,
    stars smallint CHECK (stars>=0 AND stars<=5) NOT NULL,
    comment text NOT NULL,
    UNIQUE (user_to_id, entity_from_id,entity_from_type),
    FOREIGN KEY (user_to_id) REFERENCES users(id)
);

-- ENUM в таблицу resume и vacancies в столбец status
CREATE TYPE enum_resume_vacancy_status_type AS ENUM('ACTIVE', 'INACTIVE');

CREATE TABLE resume
(
    id bigserial PRIMARY KEY,
    resume_name varchar(256) NOT NULL,
    date_created date NOT NULL,
    status enum_resume_vacancy_status_type NOT NULL,
    user_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE experience
(
    id bigserial PRIMARY KEY,
    resume_id bigint NOT NULL,
    description text NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    CHECK (start_date<end_date),
    FOREIGN KEY (resume_id) REFERENCES resume(id),
    CHECK (start_date<end_date)
);

CREATE TABLE skills
(
    id bigserial PRIMARY KEY,
    skill_name varchar(128) NOT NULL
);

CREATE TABLE resume_skill_link
(
    resume_id bigint NOT NULL,
    skill_id bigint NOT NULL,
    PRIMARY KEY (resume_id, skill_id),
    FOREIGN KEY (resume_id) REFERENCES resume(id),
    FOREIGN KEY (skill_id) REFERENCES skills(id)
);

CREATE TABLE companies
(
    id bigserial PRIMARY KEY,
    company_name varchar(256) UNIQUE NOT NULL,
    email varchar(128) UNIQUE NOT NULL,
    location varchar(128) NOT NULL,
    role enum_role_type DEFAULT 'USER' NOT NULL,
    password varchar(128) NOT NULL,
    activation_code varchar(60)
);

CREATE TABLE company_feedback
(
    id bigserial PRIMARY KEY,
    company_to_id bigint NOT NULL,
    user_from_id bigint NOT NULL,
    stars smallint CHECK (stars>=0 AND stars<=5) NOT NULL,
    comment text NOT NULL,
    UNIQUE (company_to_id, user_from_id),
    FOREIGN KEY (company_to_id) REFERENCES companies(id),
    FOREIGN KEY (user_from_id) REFERENCES users(id)
);



CREATE TABLE vacancies
(
    id bigserial PRIMARY KEY,
    company_id bigint NOT NULL,
    date_created date NOT NULL,
    header varchar (256) NOT NULL,
    description text NOT NULL,
    salary int DEFAULT 0 NOT NULL,
    experience varchar(256),
    status enum_resume_vacancy_status_type NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies(id)
);

CREATE TABLE vacancies_skill_link
(
    vacancy_id bigint NOT NULL,
    skill_id bigint NOT NULL,
    PRIMARY KEY (vacancy_id, skill_id),
    FOREIGN KEY (vacancy_id) REFERENCES vacancies(id),
    FOREIGN KEY (skill_id) REFERENCES skills(id)
);

-- ENUM в таблицу vacancy_responses в столбец status
CREATE TYPE enum_vacancy_responses_status_type AS ENUM
    ('INVITE', 'REFUSAL', 'VIEWED','NOT_VIEWED');

CREATE TABLE vacancy_responses
(
    id bigserial PRIMARY KEY,
    vacancy_id bigint NOT NULL,
    user_id bigint NOT NULL,
    message text,
    status enum_vacancy_responses_status_type NOT NULL,
    UNIQUE (vacancy_id, user_id),
    FOREIGN KEY (vacancy_id) REFERENCES vacancies(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- ENUM в таблицу messages в столбец is_read
CREATE TYPE enum_messages_is_read_type  AS ENUM ('READ','NO_READ');

CREATE TABLE messages
(
    id bigserial PRIMARY KEY,
    user_id bigint NOT NULL,
    company_id bigint NOT NULL,
    from_entity_message enum_user_company_type NOT NULL,
    date_created timestamp without time zone NOT NULL,
    is_read enum_messages_is_read_type NOT NULL,
    message text NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (company_id) REFERENCES companies(id)
);

--Команды для преобразования ENUM с кода в ENUM БД
CREATE CAST (character varying as enum_user_company_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_resume_vacancy_status_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_vacancy_responses_status_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_messages_is_read_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_role_type) WITH INOUT AS IMPLICIT;

CREATE TABLE projects
(
    id bigserial PRIMARY KEY,
    project_name varchar(256) NOT NULL,
    description text NOT NULL,
    requirement text NOT NULL,
    date_start date NOT NULL,
    date_end date NOT NULL,
    company_id bigint NOT NULL,
    CHECK (date_start<date_end),
    FOREIGN KEY (company_id) REFERENCES companies(id)

);

CREATE TABLE user_project_link
(
    user_id bigint NOT NULL,
    project_id bigint NOT NULL,
    PRIMARY KEY (user_id, project_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);