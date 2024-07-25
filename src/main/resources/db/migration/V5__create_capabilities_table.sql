CREATE TABLE IF NOT EXISTS Capabilities (
    capabilityId INT AUTO_INCREMENT PRIMARY KEY,
    capabilityName VARCHAR(64) NOT NULL
    );

    INSERT INTO Capabilities (capabilityName) VALUES
    ('Applied Innovation'),
    ('Business Development & Marketing'),
    ('Business Services Support'),
    ('Cyber Security'),
    ('Data & AI'),
    ('Delivery Management'),
    ('Engineering'),
    ('Experience Design'),
    ('Platforms'),
    ('Pre-Sales'),
    ('Product & Digital Advisory'),
    ('Quality Assurance');
