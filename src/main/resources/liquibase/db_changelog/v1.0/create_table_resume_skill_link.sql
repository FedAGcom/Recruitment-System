--liquibase formatted sql
--changeset Artem:create-table-resume_skill_link
CREATE TABLE resume_skill_link
(
    resume_id bigint NOT NULL,
    skill_id  bigint NOT NULL,
    PRIMARY KEY (resume_id, skill_id),
    FOREIGN KEY (resume_id) REFERENCES resume (id),
    FOREIGN KEY (skill_id) REFERENCES skills (id)
);

