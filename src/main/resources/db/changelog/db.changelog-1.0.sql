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