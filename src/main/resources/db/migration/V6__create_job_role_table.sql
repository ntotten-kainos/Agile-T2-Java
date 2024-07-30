CREATE TABLE IF NOT EXISTS JobRoles (
    jobRoleId TINYINT NOT NULL AUTO_INCREMENT,
    roleName VARCHAR(64) NOT NULL,
    `description` VARCHAR(500) NOT NULL,
    responsibilities VARCHAR(500) NOT NULL,
    location VARCHAR(50) NOT NULL,
    bandId TINYINT NOT NULL,
    capabilityId TINYINT NOT NULL,
    closingDate DATETIME NOT NULL,
    `status` BOOLEAN NOT NULL,
    specification VARCHAR(300) NOT NULL,
    CONSTRAINT specification_check CHECK (specification REGEXP 'https://'),
    PRIMARY KEY (jobRoleId),
    FOREIGN KEY (bandId) REFERENCES Bands(bandId),
    FOREIGN KEY (capabilityId) REFERENCES Capabilities(capabilityId)
);

INSERT INTO JobRoles (roleName, `description`, responsibilities, location, bandId, capabilityId, closingDate, `status`, specification) VALUES
('Trainee UX Designer', 
'As a UX Designer, you will deliver intuitive service experiences based on user needs and design principles. You will be passionate about needs-based design and an advocate for design thinking and service design.',
'Good understanding of User-Centred Design, Able to interpret styleguides and frameworks and understand how to apply them, Able to interpret user journey maps and apply in the design process, Can translate user journeys into prototype using appropriate tool or code', 
'Belfast', 1, 7, '2024-12-31 23:59:59', TRUE, 'https://kainossoftwareltd.sharepoint.com/:w:/r/experience%20design/_layouts/15/Doc.aspx?sourcedoc=%7BFE334F05-BC76-46EE-A063-8037805DB949%7D&file=B6%20Trainee.docx&action=default&mobileredirect=true'),

('Associate User Researcher',
'As an experienced Service Designer, you will be responsible for delivering end-to-end, efficient, and consistent service experiences. You will be an advocate for needs-based design, design research, design thinking and service design, and are passionate about guiding others in these principles.',
'Plan, design and conduct research activities, including producing discussion guides and participant screening documentation, Good understanding of qualitative research methods, Knowledge of quantitative research methods, Understands how to interpret and draw analysis from research findings', 
'Derry', 2, 5, '2024-11-30 23:59:59', TRUE, 'https://kainossoftwareltd.sharepoint.com/:w:/r/experience%20design/_layouts/15/Doc.aspx?sourcedoc=%7B03B0CDEE-F342-4CCA-81BF-26B4B5C11FA0%7D&file=B5%20Associate.docx&action=default&mobileredirect=true'),

('Consultant Service Designer', 
'We are looking for an experienced and driven Project Manager to lead and manage projects from initiation to completion. The Project Manager will be responsible for planning, executing, and delivering projects on time, within scope, and within budget. The ideal candidate will possess strong organizational skills, excellent communication abilities, and a track record of successful project management.', 
'Provide clear visibility of the end-to-end service experience, Input to and influence the future of the service experience, Familiar with a range of strategic models and can apply them, Understanding of business analysis in order to form problem statements',
'Toronto', 3, 10, '2024-10-31 23:59:59', FALSE, 'https://kainossoftwareltd.sharepoint.com/:w:/r/experience%20design/_layouts/15/Doc.aspx?sourcedoc=%7BCC765806-EEE7-4703-BFFC-7BA1695F16D7%7D&file=B3%20Consultant.docx&action=default&mobileredirect=true'),

('Experience Strategy Lead', 
'As an Experience Strategy Lead you will be responsible for leading multichannel experience strategy engagements. You will take part in all phases of experience strategy, from context framing to proposition development, delivery, and on-going consultation. You will be a thought leader and a passionate advocate of user-centred design, design research, design thinking and service design.', 
'Lead experience strategy projects, Exploit current and emerging trends to identify and lead improvements to existing services as well as introduce innovative and industry first service experiences, Leverage user insight, market insight, service design, data analysis, and technology to create actionable experience strategies', 
'Belfast', 1, 4, '2024-09-30 23:59:59', TRUE, 'https://kainossoftwareltd.sharepoint.com/:w:/r/experience%20design/_layouts/15/Doc.aspx?sourcedoc=%7B3E13433F-93E0-4BF6-94A0-41C4ED5E267D%7D&file=B2%20Lead.docx&action=default&mobileredirect=true');


