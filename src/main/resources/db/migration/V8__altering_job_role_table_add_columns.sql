TRUNCATE TABLE JobRoles;
 
ALTER TABLE JobRoles
 ADD COLUMN `description` VARCHAR(500) NOT NULL,
 ADD COLUMN responsibilities VARCHAR(500) NOT NULL,
 ADD COLUMN specification VARCHAR(300) NOT NULL;