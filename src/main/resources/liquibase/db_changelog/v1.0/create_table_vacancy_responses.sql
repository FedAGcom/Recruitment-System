--liquibase formatted sql
--changeset Artem:create-table-vacancy_responses
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

