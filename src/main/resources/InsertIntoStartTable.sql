ALTER SEQUENCE users_id_seq RESTART WITH 1;

INSERT INTO users(first_name, last_name, email, birthday, role, password, activation_code, active_status)
VALUES ('Ivan', 'Ivanov', 'ivanov@mail.ru', '1993-10-12', 'ADMIN', '$2a$12$b/NkRaQe8WMapsg2KpGDH.0sOXyPwwAB5ucOId6M3vSAkpg4e1x1i', NULL, 'ACTIVE'),
       ('Petr', 'Petrov', 'petrov@mail.ru', '1994-11-17', 'ADMIN', '$2a$12$klPFBHwuzchLqQrJaAi9qOD7WJVcVMtw952u8xxa6ceAtrBs05/5e', NULL, 'ACTIVE'),
       ('Aleksandr', 'Aleksandrov', 'aleksandrov@mail.ru', '1990-01-12', 'USER', '$2a$12$BijtK3QuZPxXbggSp5IfS.zy4yZZUKiFwjkBpBTz9yzt0.hNfOxoa', NULL, 'ACTIVE');

ALTER SEQUENCE entrance_exams_id_seq RESTART WITH 1;

INSERT INTO entrance_exams(user_id, score)
VALUES (1, 80),
       (2, 30),
       (3, 90);

ALTER SEQUENCE user_feedback_id_seq RESTART WITH 1;

INSERT INTO user_feedback(user_to_id, entity_from_id, entity_from_type, stars, comment)
VALUES (1, 2, 'USER', 4, 'красава'),
       (2, 1, 'USER', 4, 'красава'),
       (1, 3, 'USER', 4, ' не красава');

ALTER SEQUENCE resume_id_seq RESTART WITH 1;

INSERT INTO resume(resume_name, date_created, status, user_id)
VALUES ('Java junior', '2000-10-10', 'ACTIVE', 1),
       ('Java junior', '2005-10-10', 'ACTIVE', 2),
       ('C++ junior', '2010-10-10', 'INACTIVE', 3);

ALTER SEQUENCE experience_id_seq RESTART WITH 1;

INSERT INTO experience(resume_id, description, start_date, end_date)
VALUES (1, 'Грузчик', '2015-10-12', '2020-10-12'),
       (2, 'Грузчик', '2015-10-12', '2019-10-12'),
       (2, 'Стажёр в IT компании', '2019-10-12', '2020-10-12'),
       (3, 'Бухгалтер', '2000-10-12', '2020-10-12');

ALTER SEQUENCE skills_id_seq RESTART WITH 1;

INSERT INTO skills(skill_name)
VALUES ('Hibernate'),
       ('Java'),
       ('Spring');

INSERT INTO resume_skill_link (resume_id, skill_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 3),
       (3, 3);

ALTER SEQUENCE companies_id_seq RESTART WITH 1;

INSERT INTO companies(company_name, email, location, role, password, activation_code, active_status)
VALUES ('FedAG', 'fedag@mail.ru', 'SPB', 'ADMIN', '$2a$12$H/i3lZQBo2Ob82g.ois0TOawpEnqqou7CsfgYwxDghqP2OlwbDuPi', NULL, 'ACTIVE'),
       ('Epam', 'epam@mail.ru', 'SPB', 'USER', '$2a$12$1IYavMxyi/k1HbLX9jBoQ.QCmOhHZWo3qT/mRAcKPN5NfDOGZBIDq', NULL, 'ACTIVE'),
       ('SomeCompany', 'somecompany@mail.ru', 'Moscow', 'USER', '$2a$12$L7.dCU8RHMFaR7Wbyu/GsuyQtZvGevjvQcW4TzydKsiKOA4BXUJm2', NULL, 'ACTIVE');

ALTER SEQUENCE company_feedback_id_seq RESTART WITH 1;

INSERT INTO company_feedback(company_to_id, user_from_id, stars, comment)
VALUES (1, 1, 5, 'хороши'),
       (1, 2, 5, 'хороши'),
       (2, 3, 2, 'не хороши'),
       (3, 2, 4, 'норм');

ALTER SEQUENCE vacancies_id_seq RESTART WITH 1;

INSERT INTO vacancies(company_id, date_created, header, description, salary, experience, status)
VALUES (1, '2000-10-10', 'Java Junior', 'Требуются знания основ ООП', 0, 'не требуется', 'ACTIVE'),
       (1, '2001-10-10', 'C++ Junior', 'Требуются знания основ C++', 0, 'не требуется', 'ACTIVE'),
       (2, '2002-10-10', 'Java Junior', 'Знания JAVA EE', 0, 'опыт работы от полугода в сфере IT', 'INACTIVE'),
       (3, '2003-10-10', 'FrontEnd Developer', 'Требуются знания основ ООП', 0, 'от 1 года в сфере IT', 'INACTIVE');

INSERT INTO vacancies_skill_link
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 3),
       (4, 1),
       (4, 2),
       (4, 3);

ALTER SEQUENCE vacancy_responses_id_seq RESTART WITH 1;

INSERT INTO vacancy_responses(vacancy_id, user_id, message, status)
VALUES (1, 1, 'Ждем вас на Восстания в 17-00', 'INVITE'),
       (1, 2, NULL, 'NOT_VIEWED'),
       (2, 3, 'Вы нам не подходите', 'REFUSAL'),
       (3, 2, NULL, 'VIEWED');

ALTER SEQUENCE messages_id_seq RESTART WITH 1;

INSERT INTO messages(user_id, company_id, from_entity_message, date_created, is_read, message)
VALUES (1, 1, 'USER', '2022-05-28 04:05:06', 'READ', 'Что по дресс коду?'),
       (1, 1, 'COMPANY', '2022-05-28 04:05:20', 'READ', 'Свободный'),
       (2, 1, 'USER', '2022-05-27 06:15:20', 'NO_READ', 'Есть у вас перспективы трудоустройства?'),
       (3, 2, 'COMPANY', '2022-05-20 14:25:20', 'READ', 'Не хотели бы вы рассмотреть наши вакансии?');

ALTER SEQUENCE projects_id_seq RESTART WITH 1;

INSERT INTO projects(project_name, description, requirement, date_start, date_end, company_id)
VALUES ('Java project', 'project1', 'Java', '2022-06-01', '2022-06-22', 2),
       ('C# project', 'project2', 'C#', '2022-06-01', '2022-06-22', 2),
       ('C++ project', 'project1', 'C++', '2022-06-01', '2022-06-22', 1);

INSERT INTO user_project_link
VALUES (1, 1),
       (1, 2),
       (2, 2);
