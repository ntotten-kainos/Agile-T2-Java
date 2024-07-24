CREATE TABLE IF NOT EXISTS `UserRoles` (
	`userRoleId` tinyint PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`userRoleName` VARCHAR(64) NOT NULL
);

INSERT INTO `UserRoles`(`userRoleName`) VALUES ('ADMIN'), ('HR');