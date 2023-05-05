--liquibase formatted sql

--changeset jjasinovski:1
CREATE TABLE IF NOT EXISTS `oauthService`.`roles` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;
--rollback DROP TABLE roles;

--changeset jjasinovski:2
CREATE TABLE IF NOT EXISTS `oauthService`.`users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

--changeset jjasinovski:3
CREATE TABLE IF NOT EXISTS `oauthService`.`user_roles` (
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;
