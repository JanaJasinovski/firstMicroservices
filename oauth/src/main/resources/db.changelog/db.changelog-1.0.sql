--liquibase formatted sql


--changeset jjasinovski:1
CREATE TABLE roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);
