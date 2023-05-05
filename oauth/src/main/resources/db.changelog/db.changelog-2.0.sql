--liquibase formatted sql

--changeset jjasinovski:1
INSERT INTO `oauthService`.`roles`(name) VALUES ('ADMIN');
INSERT INTO `oauthService`.`roles`(name) VALUES ('USER');

--changeset jjasinovski:2
INSERT INTO `oauthService`.`users` (username, password) VALUES ('admin', '{bcrypt}admin');
INSERT INTO `oauthService`.`users` (username, password) VALUES ('jana', '{bcrypt}jana');
INSERT INTO `oauthService`.`users` (username, password) VALUES ('denis', '{bcrypt}denis');

--changeset jjasinovski:3
INSERT INTO `oauthService`.`user_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `oauthService`.`user_roles` (user_id, role_id) VALUES (2, 2);
INSERT INTO `oauthService`.`user_roles` (user_id, role_id) VALUES (3, 2);



