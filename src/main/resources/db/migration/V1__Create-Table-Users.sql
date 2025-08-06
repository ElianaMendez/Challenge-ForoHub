CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    login VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50)
);

