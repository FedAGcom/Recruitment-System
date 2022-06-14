--liquibase formatted sql
--changeset Artem:create-table-vacancies
CREATE TABLE vacancies
(
    id bigserial PRIMARY KEY,
    company_id bigint NOT NULL,
    header varchar (256) NOT NULL,
    description text NOT NULL,
    salary int DEFAULT 0 NOT NULL,
    experience varchar(256),
    status enum_resume_vacancy_status_type NOT NULL,
    date_created DATE NOT NULL,
    FOREIGN KEY (company_id) REFERENCES companies(id)
);

