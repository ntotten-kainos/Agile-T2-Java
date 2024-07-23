CREATE TABLE `Users` (
	`email` VARCHAR(64) PRIMARY KEY NOT NULL,
    `password` VARCHAR(256) NOT NULL
);

INSERT INTO `Users`(`email`, `password`)
VALUES  ('valid.admin@email.com', 'admin!Pa$$word123'),
        ('user.name@gmail.com', '$up3rSecret%321');
