INSERT INTO `users` (`username`,
                     `full_name`,
                     `password`,
                     `account_non_expired`,
                     `account_non_locked`,
                     `credentials_non_expired`,
                     `enabled`) VALUES ('foo', 'foo bar ', '$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS', b'1', b'1', b'1', b'1'),
                                       ('bar', 'bar foo ', '$2a$16$h4yDQCYTy62R6xrtFDWONeMH3Lim4WQuU/aj8hxW.dJJoeyvtEkhK', b'1', b'1', b'1', b'1'),
                                       ('john', 'john due ', '123', b'1', b'1', b'1', b'1');