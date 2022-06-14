--liquibase formatted sql
--changeset Artem:create-table-projects
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

