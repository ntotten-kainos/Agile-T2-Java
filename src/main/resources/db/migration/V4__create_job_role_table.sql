CREATE TABLE JobRoles (
	jobRoleId TINYINT NOT NULL AUTO_INCREMENT,
	roleName VARCHAR(64) NOT NULL,
	location VARCHAR(50) NOT NULL,
    capability VARCHAR(50) NOT NULL,
    band VARCHAR(50) NOT NULL,
    closingDate DateTime NOT NULL,
    status BOOLEAN NOT NULL,
    PRIMARY KEY (jobRoleId));

     INSERT INTO JobRoles (roleName, location, capability, band, closingDate, status) VALUES
    ('Software Engineer', 'Belfast', 'Engineering', 'BAND1', '2024-12-31 23:59:59', true),
    ('Data Scientist', 'Derry', 'Data & AI', 'BAND2', '2024-11-30 23:59:59', true),
    ('Product Manager', 'Toronto', 'Product & Digital Advisory', 'BAND3', '2024-10-31 23:59:59', false),
    ('Cyber Security Analyst', 'Belfast', 'Cyber Security', 'BAND1', '2024-09-30 23:59:59', true);