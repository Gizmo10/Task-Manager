\c koyebdb;

CREATE TABLE IF NOT EXISTS tasks(
    unique_id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    created_at VARCHAR(10) NOT NULL,
    completed_at VARCHAR(10),
    status VARCHAR(15) NOT NULL
);

SELECT * FROM tasks;