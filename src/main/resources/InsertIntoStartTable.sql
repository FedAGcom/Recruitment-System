
    INSERT INTO users(first_name, last_name,email, birthday)
VALUES
    ('Ivan','Ivanov','ivanov@mail.ru', '1993-10-12'),
    ('Petr','Petrov','petrov@mail.ru', '1994-11-17'),
    ('Aleksandr','Aleksandrov','aleksandrov@mail.ru', '1990-01-12');

    INSERT INTO entrance_exams(user_id , score)
VALUES
    (1,80),
    (2,30),
    (3,90);

    INSERT INTO user_feedback(user_to_id, entity_from_id, entity_from_type, stars, comment)
VALUES
    (1,2,'USER',4,'красава'),
    (2,1,'USER',4,'красава'),
    (1,3,'USER',4,' не красава');

    INSERT INTO resume(resume_name, status, user_id)
VALUES
    ('Java junior', 'ACTIVE',1),
    ('Java junior', 'ACTIVE',2),
    ('C++ junior', 'INACTIVE',3);

    INSERT INTO experience(resume_id, description, start_date, end_date)
VALUES
    (1,'Грузчик','2015-10-12','2020-10-12'),
    (2,'Грузчик','2015-10-12','2019-10-12'),
    (2,'Стажёр в IT компании','2019-10-12','2020-10-12'),
    (3,'Бухгалтер','2000-10-12','2020-10-12');

    INSERT INTO skills(skill_name)
VALUES('Hibernate'),('Java'),('Spring');

    INSERT INTO resume_skill_link (resume_id, skill_id)
VALUES(1,1),(1,2),(1,3),
    (2,1),(2,3),
    (3,3);

    INSERT INTO companies(company_name, email, location)
VALUES('FedAG','fedag@mail.ru','SPB'),
    ('Epam','epam@mail.ru','SPB'),
    ('SomeCompany','somecompany@mail.ru','Moscow');

    INSERT INTO company_feedback(company_to_id, user_from_id, stars, comment)
VALUES(1,1,5,'хороши'),
    (1,2,5,'хороши'),
    (2,3,2,'не хороши'),
    (3,2,4,'норм');

    INSERT INTO vacancies(company_id, header, description, salary, experience, status)
VALUES(1,'Java Junior','Требуются знания основ ООП', NULL,'не требуется','ACTIVE'),
    (1,'C++ Junior','Требуются знания основ C++', NULL,'не требуется','ACTIVE'),
    (2,'Java Junior','Знания JAVA EE', NULL,'опыт работы от полугода в сфере IT','INACTIVE'),
    (3,'FrontEnd Developer','Требуются знания основ ООП', 2000, 'от 1 года в сфере IT','INACTIVE');

    INSERT INTO vacancies_skill_link
VALUES(1,1), (1,2),
    (2,1),
    (3,3),
    (4,1), (4,2), (4,3);

    INSERT INTO vacancy_responses(vacancy_id, user_id, message, status)
VALUES(1,1,'Ждем вас на Восстания в 17-00', 'INVITE'),
    (1,2,NULL, 'NOT_VIEWED'),
    (2,3,'Вы нам не подходите', 'REFUSAL'),
    (3,2,NULL, 'VIEWED');

    INSERT INTO messages(user_id, company_id, from_entity_message, date_created, is_read, message)
VALUES(1,1,'USER', '2022-05-28 04:05:06','READ','Что по дресс коду?'),
    (1,1,'COMPANY', '2022-05-28 04:05:20','READ','Свободный'),
    (2,1,'USER', '2022-05-27 06:15:20','NO_READ','Есть у вас перспективы трудоустройства?'),
    (3,2,'COMPANY', '2022-05-20 14:25:20','READ','Не хотели бы вы рассмотреть наши вакансии?');

    INSERT INTO projects(project_name, description,requirement, date_start, date_end,company_id)
    VALUES
        ('Java project','project1','Java','2022-06-01','2022-06-22',2),
        ('C# project','project2','C#','2022-06-01','2022-06-22',2),
        ('C++ project','project1','C++','2022-06-01','2022-06-22',1);

        INSERT INTO user_project_link
    VALUES
        (1,1),
        (1,2),
        (2,2);
