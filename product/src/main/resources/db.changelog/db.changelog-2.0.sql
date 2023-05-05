--liquibase formatted sql

--changeset jjasinovski:1
INSERT INTO products(name, price, amount, user_id) VALUES ('Xiaomi 13', 2600, 5, 1);
INSERT INTO products(name, price, amount, user_id) VALUES ('HONOR X9a', 850, 9, 1);
INSERT INTO products(name, price, amount, user_id) VALUES ('POCO X5 Pro', 1060, 6, 1);
INSERT INTO products(name, price, amount, user_id) VALUES ('Oppo A96 CPH2333', 699, 12, 1);
INSERT INTO products(name, price, amount, user_id) VALUES ('Infinix Hot 20i', 319, 25, 1);



