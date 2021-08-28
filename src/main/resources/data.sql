INSERT IGNORE INTO `modern_ecommerce`.`user` (`id`, `username`, `password`) VALUES ('1', 'john', '12345');
INSERT IGNORE INTO `modern_ecommerce`.`user` (`id`, `username`, `password`) VALUES ('1', 'jane', '12345');

INSERT IGNORE INTO `modern_ecommerce`.`authority` (`id`, `name`, `user`) VALUES ('1',
                                                                       'READ', '1');
INSERT IGNORE INTO `modern_ecommerce`.`authority` (`id`, `name`, `user`) VALUES ('2',
                                                                       'WRITE', '1');
INSERT IGNORE INTO `modern_ecommerce`.`product` (`id`, `name`, `price`, `currency`)
VALUES ('1', 'Chocolate', '10', 'USD');