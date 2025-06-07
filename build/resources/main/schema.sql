CREATE TABLE IF NOT EXISTS tasks(
    unique_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    created_at VARCHAR(10) NOT NULL,
    completed_at VARCHAR(10),
    status VARCHAR(15) NOT NULL
);

SELECT * FROM tasks;