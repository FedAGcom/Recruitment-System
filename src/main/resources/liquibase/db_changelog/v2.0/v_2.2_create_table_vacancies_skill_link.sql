--liquibase formatted sql
--changeset Artem:create-table-vacancies_skill_link
CREATE TABLE vacancies_skill_link
(
    vacancy_id bigint NOT NULL,
    skill_id bigint NOT NULL,
    PRIMARY KEY (vacancy_id, skill_id),
    FOREIGN KEY (vacancy_id) REFERENCES vacancies(id),
    FOREIGN KEY (skill_id) REFERENCES skills(id)
);

