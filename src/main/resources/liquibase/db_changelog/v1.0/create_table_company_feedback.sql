--liquibase formatted sql
--changeset Artem:create-table-company_feedback
CREATE TABLE company_feedback
(
    id            bigserial PRIMARY KEY,
    company_to_id bigint                                     NOT NULL,
    user_from_id  bigint                                     NOT NULL,
    stars         smallint CHECK (stars >= 0 AND stars <= 5) NOT NULL,
    comment       text                                       NOT NULL,
    UNIQUE (company_to_id, user_from_id),
    FOREIGN KEY (company_to_id) REFERENCES companies (id),
    FOREIGN KEY (user_from_id) REFERENCES users (id)
);

