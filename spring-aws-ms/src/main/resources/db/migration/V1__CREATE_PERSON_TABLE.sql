CREATE TABLE IF NOT EXISTS `person` (`id` bigint NOT NULL AUTO_INCREMENT,`address` varchar(255) DEFAULT NULL,
    `first_name` varchar(20) NOT NULL,
    `gender` varchar(30) DEFAULT NULL,
    `last_name` varchar(80) NOT NULL,
    PRIMARY KEY (`id`)
    )