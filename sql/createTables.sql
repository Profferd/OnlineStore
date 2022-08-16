DROP DATABASE store;

CREATE DATABASE IF NOT EXISTS store;

USE store;

CREATE TABLE `role` (
                        `id` INT(10) NOT NULL AUTO_INCREMENT,
                        `role` VARCHAR(15) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                        PRIMARY KEY (`id`) USING BTREE,
                        UNIQUE INDEX `role` (`role`) USING BTREE
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=3
;


CREATE TABLE `promotion` (
                             `id` INT(10) NOT NULL AUTO_INCREMENT,
                             `name` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                             `description` TEXT NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                             `start_date` DATE NOT NULL,
                             `end_date` DATE NOT NULL,
                             `photo` VARCHAR(200) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                             `discount` TINYINT(3) NOT NULL,
                             PRIMARY KEY (`id`) USING BTREE
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=3
;

CREATE TABLE `user` (
                        `id` INT(10) NOT NULL AUTO_INCREMENT,
                        `userInfo_id` INT(10) NOT NULL,
                        `role_id` INT(10) NOT NULL,
                        `email` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                        `password` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                        PRIMARY KEY (`id`) USING BTREE,
                        UNIQUE INDEX `email` (`email`) USING BTREE,
                        INDEX `FK_userinfo_id` (`userInfo_id`) USING BTREE,
                        INDEX `FK_role_id` (`role_id`) USING BTREE,
                        CONSTRAINT `FK_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
                        CONSTRAINT `FK_userinfo_id` FOREIGN KEY (`userInfo_id`) REFERENCES `userinfo` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=5
;

CREATE TABLE `userinfo` (
                            `id` INT(10) NOT NULL AUTO_INCREMENT,
                            `name` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                            `surname` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                            `phone` BIGINT(20) UNSIGNED NOT NULL,
                            PRIMARY KEY (`id`) USING BTREE
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=5
;

CREATE TABLE `userorder` (
                             `id` INT(10) NOT NULL AUTO_INCREMENT,
                             `address` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                             `order_date` DATE NOT NULL,
                             `delivery_date` DATE NOT NULL,
                             `status` VARCHAR(20) NOT NULL DEFAULT 'pending' COLLATE 'utf8mb4_0900_ai_ci',
                             PRIMARY KEY (`id`) USING BTREE
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4
;

CREATE TABLE `product` (
                           `id` INT(10) NOT NULL AUTO_INCREMENT,
                           `category_id` INT(10) NOT NULL,
                           `promotion_id` INT(10) NULL DEFAULT NULL,
                           `name` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                           `description` TEXT NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                           `price` DOUBLE NOT NULL,
                           `status` TINYINT(3) NOT NULL DEFAULT '0',
                           `photo` VARCHAR(200) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                           `orderNumber` INT(10) NOT NULL,
                           PRIMARY KEY (`id`) USING BTREE,
                           INDEX `FK_category_id` (`category_id`) USING BTREE,
                           INDEX `FK_promotion_id` (`promotion_id`) USING BTREE,
                           CONSTRAINT `FK_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
                           CONSTRAINT `FK_promotion_id` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=5
;

CREATE TABLE `order` (
                         `id` INT(10) NOT NULL AUTO_INCREMENT,
                         `product_id` INT(10) NOT NULL,
                         `user_id` INT(10) NOT NULL,
                         `userOrder_id` INT(10) NULL DEFAULT NULL,
                         `number` SMALLINT(5) NOT NULL DEFAULT '0',
                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `FK_user_id` (`user_id`) USING BTREE,
                         INDEX `FK_userOrder_id` (`userOrder_id`) USING BTREE,
                         INDEX `FK_product_id` (`product_id`) USING BTREE,
                         CONSTRAINT `FK_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
                         CONSTRAINT `FK_userOrder_id` FOREIGN KEY (`userOrder_id`) REFERENCES `userorder` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
                         CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;

CREATE TABLE `category` (
                            `id` INT(10) NOT NULL AUTO_INCREMENT,
                            `category` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE INDEX `category` (`category`) USING BTREE
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4
;
