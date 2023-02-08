# CREATE SCHEMA IF NOT EXISTS testDB;

CREATE TABLE IF NOT EXISTS testDB.product
(
    id   serial primary key,
    name text    not null,
    cost decimal(19, 2) not null
);