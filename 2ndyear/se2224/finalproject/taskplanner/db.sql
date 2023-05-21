CREATE SCHEMA taskplanner;
USE taskplanner;

CREATE TABLE users(
    username VARCHAR (255) PRIMARY KEY,
    password VARCHAR (255)
);

CREATE TABLE tasks(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    short_description VARCHAR(100) NOT NULL,
    deadline DATE NOT NULL,
    priority INTEGER,
    reminder_image BOOL NOT NULL,
    entry_date DATE,
);

INSERT INTO users (username, password) VALUES
    ('muhamed', 'cicak12345')