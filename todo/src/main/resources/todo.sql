CREATE DATABASE todo;

\c todo

CREATE TABLE todo (
id SERIAL PRIMARY KEY,
task varchar(255) NOT NULL,
due date NOT NULL);
