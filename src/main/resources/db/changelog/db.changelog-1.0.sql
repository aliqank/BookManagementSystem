--liquibase formatted sql

--changeset aliqan:1
CREATE TABLE IF NOT EXISTS authors
(
    id          BIGSERIAL PRIMARY KEY,
    author_name VARCHAR(255)
);

--changeset aliqan:2
CREATE TABLE IF NOT EXISTS genres
(
    id         BIGSERIAL PRIMARY KEY,
    genre_name VARCHAR(255)
);

--changeset aliqan:3
CREATE TABLE IF NOT EXISTS books
(
    id           BIGSERIAL PRIMARY KEY,
    book_name    VARCHAR(255),
    rars         INT,
    access_level INT,
    author_id    BIGINT references authors (id)
);

--changeset aliqan:4
CREATE TABLE IF NOT EXISTS coments
(
    id          BIGSERIAL PRIMARY KEY,
    author_name VARCHAR(64),
    content     VARCHAR(400),
    favorite    BOOLEAN,
    book_id     BIGINT references books (id)
);

--changeset aliqan:5
CREATE TABLE IF NOT EXISTS book_genres
(
    book_id  BIGINT references books (id),
    genre_id BIGINT references genres (id),
    primary key (book_id, genre_id)
);

--changeset aliqan:6
CREATE TABLE IF NOT EXISTS users
(
    id            BIGSERIAL PRIMARY KEY,
    user_name     VARCHAR(255),
    password VARCHAR(255),
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    email         VARCHAR(255),
    age           INT,
    access_level  INT,
    role          VARCHAR(32)
);

--changeset aliqan:7
CREATE TABLE IF NOT EXISTS orders
(
    id           BIGSERIAL PRIMARY KEY,
    order_number VARCHAR(255),
    order_time   TIMESTAMP,
    user_id      BIGINT references users (id),
    STATUS       VARCHAR(255)
);

--changeset aliqan:8
CREATE TABLE IF NOT EXISTS instances
(
    id               BIGSERIAL PRIMARY KEY,
    inventory_number VARCHAR(255),
    book_id          BIGINT references books (id)
);

--changeset aliqan:9
CREATE TABLE IF NOT EXISTS book_orders
(
    book_id  BIGINT references books (id),
    order_id BIGINT references orders (id),
    primary key (book_id, order_id)
);

INSERT INTO GENRES (id, genre_name) VALUES(1, 'Poetry');
INSERT INTO GENRES (id, genre_name) VALUES(2, 'Fiction');
INSERT INTO GENRES (id, genre_name) VALUES(3, 'Nonfiction');
INSERT INTO GENRES (id, genre_name) VALUES(4, 'Drama');
INSERT INTO GENRES (id, genre_name) VALUES(5, 'Detective');

INSERT INTO AUTHORS (id, author_name) VALUES(1, 'Lionel Messi');
INSERT INTO AUTHORS (id, author_name) VALUES(2, 'Cristiano Ronaldo');
INSERT INTO AUTHORS (id, author_name) VALUES(3, 'Neymar Junior');
INSERT INTO AUTHORS (id, author_name) VALUES(4, 'Karim Benzema');
INSERT INTO AUTHORS (id, author_name) VALUES(5, 'Luis Suarez');

INSERT INTO BOOKS (id, author_id, book_name, rars, access_level) VALUES(1, 1, 'Introduction to Java', 12, 1);
INSERT INTO BOOKS (id, author_id, book_name, rars, access_level) VALUES(2, 1, 'Introduction to C++', 12, 1);
INSERT INTO BOOKS (id, author_id, book_name, rars, access_level) VALUES(3, 1, 'Introduction to Spring', 12, 1);
INSERT INTO BOOKS (id, author_id, book_name, rars, access_level) VALUES(4, 1, 'Introduction to Hibernate', 12, 1);
INSERT INTO BOOKS (id, author_id, book_name, rars, access_level) VALUES(5, 2, 'Introduction to MapStruct', 12, 1);
INSERT INTO BOOKS (id, author_id, book_name, rars, access_level) VALUES(6, 2, 'Introduction to Python', 12, 1);
INSERT INTO BOOKS (id, author_id, book_name, rars, access_level) VALUES(7, 3, 'Introduction to Php', 12, 1);
INSERT INTO BOOKS (id, author_id, book_name, rars, access_level) VALUES(8, 3, 'Introduction to JS', 12, 1);
INSERT INTO BOOKS (id, author_id, book_name, rars, access_level) VALUES(9, 4, 'Introduction to Scala', 12, 1);
INSERT INTO BOOKS (id, author_id, book_name, rars, access_level) VALUES(10, 5, 'Introduction to Swift', 12, 1);

INSERT INTO BOOK_GENRES (book_id, genre_id) VALUES(1, 1);
INSERT INTO BOOK_GENRES (book_id, genre_id) VALUES(2, 1);
INSERT INTO BOOK_GENRES (book_id, genre_id) VALUES(3, 1);
INSERT INTO BOOK_GENRES (book_id, genre_id) VALUES(4, 2);
INSERT INTO BOOK_GENRES (book_id, genre_id) VALUES(5, 2);
INSERT INTO BOOK_GENRES (book_id, genre_id) VALUES(6, 3);
INSERT INTO BOOK_GENRES (book_id, genre_id) VALUES(7, 3);
INSERT INTO BOOK_GENRES (book_id, genre_id) VALUES(8, 4);
INSERT INTO BOOK_GENRES (book_id, genre_id) VALUES(9, 4);
INSERT INTO BOOK_GENRES (book_id, genre_id) VALUES(10, 5);

INSERT INTO coments (id, book_id, author_name, content, favorite)
VALUES(1, 1,'Daryn', 'Wow, this book is amazing', true);
INSERT INTO coments (id, book_id, author_name, content, favorite)
VALUES(2, 1,'Alikhan', 'Wow, this book is amazing', true);
INSERT INTO coments (id, book_id, author_name, content, favorite)
VALUES(3, 1,'Zhandos', 'Wow, this book is amazing', true);
INSERT INTO coments (id, book_id, author_name, content, favorite)
VALUES(4, 2,'Alidar', 'Wow, this book is amazing', true);
INSERT INTO coments (id, book_id, author_name, content, favorite)
VALUES(5, 2,'Ansar', 'Wow, this book is amazing', true);
INSERT INTO coments (id, book_id, author_name, content, favorite)
VALUES(6, 3,'Daryn', 'Wow, this book is amazing', true);
INSERT INTO coments (id, book_id, author_name, content, favorite)
VALUES(7, 5,'Merey', 'Wow, this book is amazing', true);
INSERT INTO coments (id, book_id, author_name, content, favorite)
VALUES(8, 5,'Alisher', 'Wow, this book is amazing', true);
INSERT INTO coments (id, book_id, author_name, content, favorite)
VALUES(9, 2,'Alibek', 'Wow, this book is amazing', true);
INSERT INTO coments (id, book_id, author_name, content, favorite)
VALUES(10, 3,'Zhandos', 'Wow, this book is amazing', true);
INSERT INTO coments (id, book_id, author_name, content, favorite)
VALUES(11, 5,'Daryn', 'Wow, this book is amazing', true);

INSERT INTO USERS
(id, user_name, password, first_name, last_name, email, age, access_level,role)
VALUES(1, 'admin', '{noop}admin', 'admin', 'admin', 'admin@mail.ru', 35, 1,'ADMIN');
INSERT INTO USERS
(id, user_name, password, first_name, last_name, email, age, access_level,role)
VALUES(2, 'user', '{noop}user', 'user', 'user', 'user@mail.ru', 35, 1,'USER');

INSERT INTO USERS
(id, user_name, password, first_name, last_name, email, age, access_level)
VALUES(3, 'aliqan', '{noop}123', 'Alikhan', 'Kaidar', 'aliqan@mail.ru', 35, 1);

INSERT INTO orders
(id, order_number, order_time, status, user_id)
VALUES(1, '100','2021-05-01 12:00:00.000', 'OPEN', 2);
VALUES(2, '101','2021-05-01 12:00:00.000', 'OPEN', 2);
VALUES(3, '102','2021-05-01 12:00:00.000', 'OPEN', 3);
VALUES(4, '103','2021-05-01 12:00:00.000', 'OPEN', 2);
VALUES(5, '104','2021-05-01 12:00:00.000', 'OPEN', 3);
