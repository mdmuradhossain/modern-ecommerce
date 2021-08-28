# CREATE TABLE IF NOT EXISTS `modern_ecommerce`.`user` (
#                                                `id` INT NOT NULL AUTO_INCREMENT,
#                                                `username` VARCHAR(45) NOT NULL,
#                                                `password` TEXT NOT NULL,
#                                                PRIMARY KEY (`id`));
# CREATE TABLE IF NOT EXISTS `modern_ecommerce`.`authority` (
#                                                     `id` INT NOT NULL AUTO_INCREMENT,
#                                                     `name` VARCHAR(45) NOT NULL,
#                                                     `user` INT NOT NULL,
#                                                     PRIMARY KEY (`id`));
# CREATE TABLE IF NOT EXISTS `modern_ecommerce`.`product` (
#                                                   `id` INT NOT NULL AUTO_INCREMENT,
#                                                   `name` VARCHAR(45) NOT NULL,
#                                                   `price` VARCHAR(45) NOT NULL,
#                                                   `currency` VARCHAR(45) NOT NULL,
#                                                   PRIMARY KEY (`id`));