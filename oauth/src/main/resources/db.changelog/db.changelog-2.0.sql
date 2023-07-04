--liquibase formatted sql

--changeset jjasinovski:1
INSERT INTO roles (name) VALUES ('ADMIN'), ('USER');
