--liquibase formatted sql
--changeset Artem:create-table-messages
CREATE TABLE messages
(
    id                  bigserial PRIMARY KEY,
    user_id             bigint                      NOT NULL,
    company_id          bigint                      NOT NULL,
    from_entity_message enum_user_company_type      NOT NULL,
    date_created        timestamp without time zone NOT NULL,
    is_read             enum_messages_is_read_type  NOT NULL,
    message             text                        NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (company_id) REFERENCES companies (id)
);

