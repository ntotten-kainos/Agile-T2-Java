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