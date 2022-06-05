ALTER TABLE vacancies
    ALTER COLUMN salary
        SET DEFAULT 0;

ALTER TABLE vacancies
    ALTER COLUMN salary
        SET NOT NULL;