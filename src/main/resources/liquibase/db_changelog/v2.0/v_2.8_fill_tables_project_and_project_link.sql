
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