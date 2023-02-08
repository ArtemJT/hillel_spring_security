CREATE SCHEMA IF NOT EXISTS test_db;

CREATE TABLE IF NOT EXISTS test_db.product
(
    id     serial primary key,
    "name" text    not null,
    "cost" numeric not null
);