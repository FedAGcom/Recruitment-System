--liquibase formatted sql
--changeset Artem:create-table-email_code
CREATE TABLE email_code
(									
	email varchar(128) NOT NULL,
	code varchar(60),
	type enum_email_code_type NOT NULL,
	PRIMARY KEY (email,type)
);

