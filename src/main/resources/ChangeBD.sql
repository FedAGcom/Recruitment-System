ALTER TABLE resume
    ADD date_created date;

UPDATE resume
SET date_created = '2022-01-08'
WHERE 1 = 1;

ALTER TABLE resume
    ALTER COLUMN date_created
        SET NOT NULL;

ALTER TABLE vacancies
    ADD date_created date;

UPDATE vacancies
SET date_created = '2022-01-08'
WHERE 1 = 1;

ALTER TABLE vacancies
    ALTER COLUMN date_created
        SET NOT NULL;


--Изменения 06.06.22
CREATE TYPE enum_role_type AS ENUM('USER', 'ADMIN');

ALTER TABLE users
    ADD COLUMN role enum_role_type DEFAULT 'USER' NOT NULL;

ALTER TABLE users
    ADD COLUMN password varchar(128);

UPDATE users
SET password = '12345'
WHERE 1=1;

ALTER TABLE users
    ALTER COLUMN  password
        SET NOT NULL;

ALTER TABLE companies
    ADD COLUMN role enum_role_type DEFAULT 'USER' NOT NULL;

ALTER TABLE companies
    ADD COLUMN password varchar(128);

UPDATE companies
SET password = '12345'
WHERE 1=1;

ALTER TABLE companies
    ALTER COLUMN  password
        SET NOT NULL;
