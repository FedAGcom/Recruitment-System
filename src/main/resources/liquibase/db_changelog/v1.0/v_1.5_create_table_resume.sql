--liquibase formatted sql
--changeset Artem:create-table-resume
CREATE TABLE resume
(
    id bigserial PRIMARY KEY,
    resume_name varchar(256) NOT NULL,
    status enum_resume_vacancy_status_type NOT NULL,
    user_id bigint NOT NULL,
    date_created DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

