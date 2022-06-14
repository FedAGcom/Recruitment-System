--liquibase formatted sql
--changeset Artem:create-table-user_feedback
CREATE TABLE user_feedback
(
    id bigserial PRIMARY KEY,
    user_to_id bigint NOT NULL,
    entity_from_id bigint NOT NULL,
    entity_from_type enum_user_company_type NOT NULL,
    stars smallint CHECK (stars>=0 AND stars<=5) NOT NULL,
    comment text NOT NULL,
    UNIQUE (user_to_id, entity_from_id,entity_from_type),
    FOREIGN KEY (user_to_id) REFERENCES users(id)
);

