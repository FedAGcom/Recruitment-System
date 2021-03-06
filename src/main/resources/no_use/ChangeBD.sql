ALTER TABLE resume
    ADD date_created date;

UPDATE resume
SET date_created = '2022-01-08'
WHERE 1 = 1;

ALTER TABLE resume
    ALTER COLUMN date_created
        SET NOT NULL;

ALTER TABLE vacancies
    ADD date_created date;

UPDATE vacancies
SET date_created = '2022-01-08'
WHERE 1 = 1;

ALTER TABLE vacancies
    ALTER COLUMN date_created
        SET NOT NULL;


--Изменения 06.06.22
CREATE TYPE enum_role_type AS ENUM('USER', 'ADMIN');

ALTER TABLE users
    ADD COLUMN role enum_role_type DEFAULT 'USER' NOT NULL;

ALTER TABLE users
    ADD COLUMN password varchar(128);

UPDATE users
SET password = '12345'
WHERE 1=1;

ALTER TABLE users
    ALTER COLUMN  password
        SET NOT NULL;

ALTER TABLE companies
    ADD COLUMN role enum_role_type DEFAULT 'USER' NOT NULL;

ALTER TABLE companies
    ADD COLUMN password varchar(128);

UPDATE companies
SET password = '12345'
WHERE 1=1;

ALTER TABLE companies
    ALTER COLUMN  password
        SET NOT NULL;

CREATE CAST (character varying as enum_role_type) WITH INOUT AS IMPLICIT;

--Изменения 10.06.22
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

--Изменения 11.06.22
ALTER TABLE users
    ADD activation_code varchar(60);

ALTER TABLE users
    ADD activation_code varchar(60);