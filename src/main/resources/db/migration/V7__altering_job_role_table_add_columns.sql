TRUNCATE TABLE JobRoles;
 
alter table JobRoles
 add column `description` VARCHAR(500) NOT NULL,
 add column responsibilities VARCHAR(500) NOT NULL,
 add column specification VARCHAR(300) NOT NULL,
 add constraint specification_check CHECK (specification REGEXP 'https://');