-- Закоментирована генерация таблиц с помощью Intellij IDEA
-- create table users
-- (
--     id         bigserial primary key,
--     first_name varchar(128) not null,
--     last_name  varchar(128) not null,
--     email      varchar(128) not null unique,
--     birthday   date         not null
-- );
--
-- alter table users
--     owner to postgres;
--
-- create table entrance_exams
-- (
--     id      bigserial primary key,
--     user_id bigint   not null references users,
--     score   smallint not null
--     constraint entrance_exams_score_check
--     check ((score >= 0) AND (score <= 100))
-- );
--
-- alter table entrance_exams
--     owner to postgres;
--
-- create table user_feedback
-- (
--     id               bigserial primary key,
--     user_to_id       bigint            not null references users,
--     entity_from_id   bigint            not null,
--     entity_from_type enum_user_company not null,
--     stars            smallint          not null
--     constraint user_feedback_stars_check
--     check ((stars >= 0) AND (stars <= 5)),
--     comment          text              not null,
--     unique (user_to_id, entity_from_id, entity_from_type)
-- );
--
-- alter table user_feedback
--     owner to postgres;
--
-- create table resume
-- (
--     id          bigserial primary key,
--     resume_name varchar(256)               not null,
--     status      enum_resume_vacancy_status not null,
--     user_id     bigint                     not null
--     references users
-- );
--
-- alter table resume
--     owner to postgres;
--
-- create table experience
-- (
--     id          bigserial primary key,
--     resume_id   bigint not null references resume,
--     description text   not null,
--     start_date  date   not null,
--     end_date    date   not null,
--     constraint experience_check
--     check (start_date < end_date)
-- );
--
-- alter table experience
--     owner to postgres;
--
-- create table skills
-- (
--     id         bigserial primary key,
--     skill_name varchar(128) not null
-- );
--
-- alter table skills
--     owner to postgres;
--
-- create table resume_skill_link
-- (
--     resume_id bigint not null references resume,
--     skill_id  bigint not null references skills,
--     primary key (resume_id, skill_id)
-- );
--
-- alter table resume_skill_link
--     owner to postgres;
--
-- create table companies
-- (
--     id           bigserial primary key,
--     company_name varchar(256) not null unique,
--     email        varchar(128) not null unique,
--     location     varchar(128) not null
-- );
--
-- alter table companies
--     owner to postgres;
--
-- create table company_feedback
-- (
--     id            bigserial primary key,
--     company_to_id bigint   not null references companies,
--     user_from_id  bigint   not null references users,
--     stars         smallint not null
--     constraint company_feedback_stars_check
--     check ((stars >= 0) AND (stars <= 5)),
--     comment       text     not null,
--     unique (company_to_id, user_from_id)
-- );
--
-- alter table company_feedback
--     owner to postgres;
--
-- create table vacancies
-- (
--     id          bigserial primary key,
--     company_id  bigint                     not null references companies,
--     header      varchar(256)               not null,
--     description text                       not null,
--     salary      integer,
--     experience  varchar(256),
--     status      enum_resume_vacancy_status not null
-- );
--
-- alter table vacancies
--     owner to postgres;
--
-- create table vacancies_skill_link
-- (
--     vacancy_id bigint not null references vacancies,
--     skill_id   bigint not null references skills,
--     primary key (vacancy_id, skill_id)
-- );
--
-- alter table vacancies_skill_link
--     owner to postgres;
--
-- create table vacancy_responses
-- (
--     id         bigserial primary key,
--     vacancy_id bigint                        not null references vacancies,
--     user_id    bigint                        not null references users,
--     message    text,
--     status     enum_vacancy_responses_status not null,
--     unique (vacancy_id, user_id)
-- );
--
-- alter table vacancy_responses
--     owner to postgres;
--
-- create table messages
-- (
--     id                  bigserial primary key,
--     user_id             bigint                not null references users,
--     company_id          bigint                not null references companies,
--     from_entity_message enum_user_company     not null,
--     date_created        timestamp             not null,
--     is_read             enum_messages_is_read not null,
--     message             text                  not null
-- );
--
-- alter table messages
--     owner to postgres;


CREATE DATABASE recruitment_system_test;

CREATE TABLE users
(
    id bigserial PRIMARY KEY,
    first_name varchar(128) NOT NULL,
    last_name varchar(128) NOT NULL,
    email varchar(128) UNIQUE NOT NULL,
    birthday date NOT NULL
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
    location varchar(128) NOT NULL
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
    header varchar (256) NOT NULL,
    description text NOT NULL,
    salary int,
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
    ('INVITE', 'REFUSAL', 'VIEWED','NOT VIEWED');

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
CREATE TYPE enum_messages_is_read_type  AS ENUM ('READ','NO READ');

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
)

--Команды для преобразования ENUM с кода в ENUM БД
CREATE CAST (character varying as enum_user_company_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_resume_vacancy_status_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_vacancy_responses_status_type) WITH INOUT AS IMPLICIT;
CREATE CAST (character varying as enum_messages_is_read_type) WITH INOUT AS IMPLICIT;
