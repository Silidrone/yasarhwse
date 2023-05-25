CREATE SCHEMA taskplanner;
USE taskplanner;

CREATE TABLE users
(
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255)
);

CREATE TABLE tasks
(
    id                INTEGER AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(30) NOT NULL,
    short_description VARCHAR(90) NOT NULL,
    deadline          DATE        NOT NULL,
    priority          INTEGER,
    reminder_image    BOOL        NOT NULL,
    entry_date        DATE DEFAULT (CURRENT_DATE),
    CONSTRAINT tasks_name_deadline_uc UNIQUE (name, deadline)
);

INSERT INTO tasks (name, short_description, deadline, priority, reminder_image)
VALUES ('SE2224 Final Project', 'Finish your SE2224 final project.', '2023-06-03', 10, TRUE),
       ('New sports shoes', 'Take your new sports shoes from the local cargo.', '2023-06-03', 3, TRUE),
       ('New jacket', 'Take your new jacket from the local cargo.', '2023-06-03', 3, TRUE),
       ('New slippers', 'Take your new slippers from the local cargo.', '2023-06-04', 2, FALSE),
       ('New guitar', 'Take your new guitar from the local cargo.', '2023-06-10', 7, TRUE),
       ('SE2222 Exam', 'Study for your SE2222 exam.', '2023-06-15', 8, TRUE),
       ('SE2228 Exam', 'Study for your SE2228 exam.', '2023-06-17', 10, FALSE),
       ('Russian Exam', 'Study for your Russian exam.', '2023-06-21', 6, FALSE),
       ('SE2226 Exam', 'Study for your SE2226 exam.', '2023-06-27', 10, FALSE),
       ('Residence Permit', 'Renew your Residence Permit.', '2025-06-01', 10, FALSE);


INSERT INTO users (username, password)
VALUES ('muhamed', 'cicak12345');