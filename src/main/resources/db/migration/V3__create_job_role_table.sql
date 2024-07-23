CREATE TABLE JobRoles (
	jobRoleId TINYINT NOT NULL AUTO_INCREMENT,
	roleName VARCHAR(64) NOT NULL,
	location VARCHAR(50) NOT NULL,
    capability VARCHAR(50) NOT NULL,
    band VARCHAR(50) NOT NULL,
    closingDate Date NOT NULL,
    status BOOLEAN NOT NULL,
    PRIMARY KEY (jobRoleId));