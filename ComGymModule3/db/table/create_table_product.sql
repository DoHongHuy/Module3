CREATE TABLE `codegym_module3`.`product` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `name` VARCHAR(45) NULL,
                                             `price` DOUBLE NULL,
                                             `quantity` INT NULL,
                                             `color` VARCHAR(45) NULL,
                                             `describe` VARCHAR(100) NULL,
                                             `idCategory` INT NULL,
                                             PRIMARY KEY (`id`));
