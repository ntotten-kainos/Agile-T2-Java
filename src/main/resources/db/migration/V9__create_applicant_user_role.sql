INSERT INTO `UserRoles`(`userRoleName`) VALUES ('APPLICANT');

INSERT INTO `Users`(`email`, `password`, `userRoleId`) VALUES ('regular.user@email.com', '$argon2id$v=19$m=60000,t=10,p=1$XqpmQ2+UdJjTw2rWbjY7vg$39Cse4trw5vilWP3FrPRrlUgt+uP0lSiMxl8CKd0xR0', 3);