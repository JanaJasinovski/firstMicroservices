--liquibase formatted sql

--changeset jjasinovski:1
CREATE TABLE products (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  price NUMERIC NOT NULL,
  amount BIGINT NOT NULL,
  user_id BIGINT NOT NULL
 );
--rollback DROP TABLE products;


