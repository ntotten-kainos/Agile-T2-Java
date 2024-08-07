CREATE TABLE IF NOT EXISTS JobRoles (
    jobRoleId TINYINT NOT NULL AUTO_INCREMENT,
    roleName VARCHAR(64) NOT NULL,
    location VARCHAR(50) NOT NULL,
    bandId TINYINT NOT NULL,
    capabilityId TINYINT NOT NULL,
    closingDate DATETIME NOT NULL,
    `status` BOOLEAN NOT NULL,
    PRIMARY KEY (jobRoleId),
    FOREIGN KEY (bandId) REFERENCES Bands(bandId),
    FOREIGN KEY (capabilityId) REFERENCES Capabilities(capabilityId)
);

INSERT INTO JobRoles (roleName, location, bandId, capabilityId, closingDate, status) VALUES
('Software Engineer', 'Belfast', 1, 7, '2024-12-31 23:59:59', true),
('Data Scientist', 'Derry', 2, 5, '2024-11-30 23:59:59', true),
('Product Manager', 'Toronto', 3, 10, '2024-10-31 23:59:59', false),
('Cyber Security Analyst', 'Belfast', 1, 4, '2024-09-30 23:59:59', true);