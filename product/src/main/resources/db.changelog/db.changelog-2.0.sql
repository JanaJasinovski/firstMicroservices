--liquibase formatted sql

--changeset jjasinovski:1
INSERT INTO product_category(category_name) VALUES ('HONOR');
INSERT INTO product_category(category_name) VALUES ('Xiaomi');
INSERT INTO product_category(category_name) VALUES ('POCO');
INSERT INTO product_category(category_name) VALUES ('Apple');
INSERT INTO product_category(category_name) VALUES ('Samsung');


--changeset jjasinovski:2
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('Xiaomi 13', 899, 5, 'assets/images/products/xiomi/xiaomi_13.jpeg', 2, 1);

INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('HONOR X9a', 266, 5,  'assets/images/products/honor/honor_X9a.jpeg',1, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('HONOR 70', 441, 5,  'https://content2.onliner.by/catalog/device/header/9a2e959f690a819571a3306a35c19b8c.jpeg',1, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('HONOR X8', 232, 5,  'https://content2.onliner.by/catalog/device/header/3df3afbde97dd19dc1056d6599ef92d7.jpeg',1, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('HONOR X8a', 238, 5,  'https://content2.onliner.by/catalog/device/header/0cd47ec6b206432eda6abe45c157b8e0.jpeg',1, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('HONOR X7', 266, 5,  'https://content2.onliner.by/catalog/device/header/3b307bf8affc58c8b7055f10e36d3eef.jpeg',1, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('HONOR X5', 95, 5,  'https://content2.onliner.by/catalog/device/header/5b78192ce5b1c26b4db3f9ac83d7d1bb.jpeg',1, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('HONOR X6', 129, 5,  'https://content2.onliner.by/catalog/device/header/174f1735add6e185fdc1bcb50d0991ec.jpeg',1, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('HONOR X9', 308, 5,  'https://content2.onliner.by/catalog/device/header/387a2cc8e7b53cd0a6305e594963eb95.jpeg',1, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('HONOR Magic4 Pro', 881, 5,  'https://content2.onliner.by/catalog/device/header/9d5928fb192f2be65a4cbd07323ade47.jpeg',1, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('HONOR X7a', 171, 5,  'https://content2.onliner.by/catalog/device/header/11a1d947146565622cfd8671b1f9425c.jpeg',1, 1);

INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('POCO X5 Pro', 371, 5,  'assets/images/products/poco/poco_x5_pro.jpeg', 3, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('Apple iPhone 11', 483, 5, 'assets/images/products/apple/apple_11.jpeg',  4, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('Samsung Galaxy S23', 875, 5, 'assets/images/products/samsung/samsung_S23.jpeg', 5, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('Apple iPhone 4s', 34, 5, 'https://content2.onliner.by/catalog/device/header/6aa21e2827e94d49fe3f9752eaf4b168.jpg', 4, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('Apple iPhone 5s', 22, 5, 'https://content2.onliner.by/catalog/device/header/d664738ddc9d2d7dc066c678bb913a49.jpeg', 4, 1);
INSERT INTO products(name, price, amount, picture, category_id, user_id) VALUES ('Apple iPhone 6', 54, 5, 'https://content2.onliner.by/catalog/device/header/3cbaaa855b8784a114a63af3d0073cc9.jpeg', 4, 1);




