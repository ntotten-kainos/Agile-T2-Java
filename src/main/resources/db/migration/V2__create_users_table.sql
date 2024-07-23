CREATE TABLE `Users` (
	`email` VARCHAR(64) PRIMARY KEY NOT NULL,
    `password` VARCHAR(256) NOT NULL
);

-- When inserting data into `Users` table, it must first be run through an Argon2 Password encoder with params matching the encoder
--  used by this application.
INSERT INTO `Users`(`email`, `password`)
VALUES  ('valid.admin@email.com', '$argon2id$v=19$m=60000,t=10,p=1$osWewhkwSV5FkUs0cJnugg$QOA2RDScF9etxgLirmqYJGOdsZT+NkN6syS7WJoVSE0'),
        ('user.name@gmail.com', '$argon2id$v=19$m=60000,t=10,p=1$H9sRUaEJb33/lAlfRe3vTA$VVBDjrhWez3z4CNAgDAFYSE/ukNSfh30/2Ssej8LY3E');
