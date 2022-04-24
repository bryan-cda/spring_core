CREATE TABLE IF NOT EXISTS `book` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `author` varchar(200) NOT NULL,
    `launch_date` date DEFAULT NULL,
    `price` decimal(19,2) NOT NULL,
    `title` varchar(200) NOT NULL,
    PRIMARY KEY (`id`)
    )