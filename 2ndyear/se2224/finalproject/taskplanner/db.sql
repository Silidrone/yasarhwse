CREATE SCHEMA taskplanner;
USE taskplanner;

CREATE TABLE users(
    username VARCHAR (255) PRIMARY KEY,
    password VARCHAR (255)
);

CREATE TABLE tasks(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    short_description VARCHAR(90) NOT NULL,
    deadline DATE NOT NULL,
    priority INTEGER,
    reminder_image BOOL NOT NULL,
    entry_date DATE,
    CONSTRAINT tasks_name_deadline_uc UNIQUE (name, deadline)
);

INSERT INTO tasks (name, short_description, deadline, priority, reminder_image, entry_date) VALUES
   ('boo1', 'boofoogooozoo1', '2023-12-12', 123, TRUE, '2023-05-23'),
   ('boo2', 'boofoogooozoo2', '2023-12-09', 456, FALSE, '2023-05-24'),
   ('boo3', 'boofoogooozoo3', '2023-12-10', 789, TRUE, '2023-05-25'),
   ('boo4', 'boofoogooozoo4', '2023-12-11', 23, FALSE, '2023-05-26');

INSERT INTO users (username, password) VALUES
    ('muhamed', 'cicak12345');