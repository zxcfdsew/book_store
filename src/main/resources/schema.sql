DROP TABLE IF EXISTS user_tb;
CREATE TABLE user_tb (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS category_tb;
CREATE TABLE category_tb (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS book_tb;
CREATE TABLE book_tb (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT NOT NULL,
    publisher VARCHAR(50),
    author VARCHAR(50) NOT NULL,
    register_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    book_name VARCHAR(50) NOT NULL,
    unit_price INT NOT NULL,
    discount_rate INT NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS order_tb;
CREATE TABLE order_tb (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_price INT NOT NULL,
    status VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS order_item_tb;
CREATE TABLE order_item_tb (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    `count` INT NOT NULL,
    price INT NOT NULL
);

DROP TABLE IF EXISTS review_tb;
CREATE TABLE review_tb (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL UNIQUE,
    score INT NOT NULL,
    content TEXT
);

INSERT INTO category_tb VALUES
    (1, '가정과 생활'),
    (2, '경제'),
    (3, '기술'),
    (4, 'IT'),
    (5, '과학');

INSERT INTO book_tb VALUES
    (DEFAULT, 1, '테스트 출판사', '테스트 저자', now(), '도서1', 20000, 0),
    (DEFAULT, 2, '테스트 출판사', '테스트 저자', now(), '도서2', 22000, 0),
    (DEFAULT, 3, '테스트 출판사', '테스트 저자', now(), '도서3', 25000, 5),
    (DEFAULT, 4, '테스트 출판사', '테스트 저자', now(), '도서4', 21000, 0),
    (DEFAULT, 3, '테스트 출판사', '테스트 저자', now(), '도서5', 30000, 20),
    (DEFAULT, 2, '테스트 출판사', '테스트 저자', now(), '도서6', 10000, 0),
    (DEFAULT, 3, '테스트 출판사', '테스트 저자', now(), '도서7', 40000, 10),
    (DEFAULT, 5, '테스트 출판사', '테스트 저자', now(), '도서8', 8000, 0),
    (DEFAULT, 5, '테스트 출판사', '테스트 저자', now(), '도서9', 14000, 2),
    (DEFAULT, 5, '테스트 출판사', '테스트 저자', now(), '도서10', 12000, 0),
    (DEFAULT, 1, '테스트 출판사', '테스트 저자', now(), '도서1', 20000, 0);