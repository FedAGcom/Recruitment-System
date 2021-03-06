--liquibase formatted sql
--changeset Artem:create-table-entrance_exams
CREATE TABLE entrance_exams
(
    id bigserial PRIMARY KEY,
    user_id bigint NOT NULL UNIQUE,
    score smallint CHECK (score>=0 AND score<=100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

