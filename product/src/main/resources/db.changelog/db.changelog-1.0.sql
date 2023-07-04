--liquibase formatted sql


--changeset jjasinovski:1
CREATE TABLE IF NOT EXISTS product_category (
  id BIGSERIAL PRIMARY KEY,
  category_name VARCHAR(255) NULL DEFAULT NULL
);
--rollback DROP TABLE product_category;

--changeset jjasinovski:2
CREATE TABLE products (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  price NUMERIC NOT NULL,
  amount BIGINT NOT NULL,
  picture VARCHAR(400),
  category_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES product_category (id)
 );


