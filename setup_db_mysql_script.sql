CREATE SCHEMA `customers-schema`;
USE `customers-schema`;
CREATE TABLE `customers` (
	`id` BIGINT NOT NULL,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) DEFAULT '-',
    `email` VARCHAR(50) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `customers_email_chk` CHECK (`email` LIKE '%@%.%'));
    
CREATE TABLE `customers_id_generator` (
	`next_val` BIGINT);

INSERT INTO `customers_id_generator` VALUES (1);


CREATE SCHEMA `employees-schema`;
USE `employees-schema`;
CREATE TABLE `employees` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) DEFAULT NULL,
  `department` ENUM('SALES','TECH','SUPPORT','FINANCE') DEFAULT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `employees_id_generator` (
	`next_val` BIGINT);

INSERT INTO `employees_id_generator` VALUES (1);


CREATE SCHEMA `users-schema`;
USE `users-schema`;

CREATE TABLE `roles` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `roles_role_uk` (`role`));

INSERT INTO `roles` (`role`) VALUES
	('ROLE_USER'),
    ('ROLE_MANAGER'),
    ('ROLE_ADMIN');


CREATE TABLE `users` (
  `id` INT NOT NULL,
  `username` VARCHAR(30) NOT NULL,
  `password` CHAR(60) NOT NULL,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) DEFAULT '-',
  `email` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_username_uk` (`username`)
);

CREATE TABLE `users_id_sequence` (
  `next_val` INT DEFAULT NULL
);

INSERT INTO `users_id_sequence` VALUES (2);

CREATE TABLE `users_roles` (
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
);

#create admin user: 
#username: admin
#password: admin
INSERT INTO `users` VALUES (
	1,
    'admin',
	'$2y$10$6rm4UIU.AajpJ6A7mvAFXOgpQGo3SFDedyxjS2Et0z9wFgkh0Fx4O',
	'John',
    'Connor',
    'admin@company.com');

#assigning 'admin' role to the admin user    
INSERT INTO `users_roles` VALUES (1, 3);

