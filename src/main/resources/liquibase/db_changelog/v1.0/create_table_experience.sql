--liquibase formatted sql
--changeset Artem:create-table-experience
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

