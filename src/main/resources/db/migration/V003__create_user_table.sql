CREATE TABLE IF NOT EXISTS testDB.user
(
    id       serial primary key,
    name     varchar(255) not null,
    password varchar(120) not null,
    role     varchar(20)  not null
);

-- Admin
-- login: admin
-- password: admin
INSERT INTO testDB.user (name, password, role)
VALUES ('admin', '$2a$10$bIUD5UskhHw/XoNz28sLKeJqsdkQuv5Lo00NaDnwvXhmLzUXA3iwq', 'ADMIN')
