CREATE TABLE `employee` (
  `employeeId` smallint unsigned NOT NULL AUTO_INCREMENT,
  `fName` varchar(40) DEFAULT NULL,
  `lName` varchar(40) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `address2` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `county` varchar(50) DEFAULT NULL,
  `postalCode` varchar(10) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `phoneNo` varchar(20) DEFAULT NULL,
  `salary` decimal(11,2) DEFAULT NULL,
  `bankNo` varchar(12) DEFAULT NULL,
  `nin` char(9) DEFAULT NULL,
  PRIMARY KEY (`employeeId`)
)

CREATE TABLE `deliveryEmployee` (
  `employeeId` smallint unsigned NOT NULL,
  `cv` text,
  `image` varchar(70) DEFAULT NULL,
  `favTech` tinytext,
  PRIMARY KEY (`employeeId`),
  CONSTRAINT `fkEmployeeIdDelivery` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`) ON DELETE CASCADE
)

CREATE TABLE `salesEmployee` (
  `employeeId` smallint unsigned NOT NULL AUTO_INCREMENT,
  `commissionRate` decimal(5,2) DEFAULT NULL,
  `totalSalesMonth` decimal(11,2) DEFAULT NULL,
  PRIMARY KEY (`employeeId`),
  CONSTRAINT `fkEmployeeIdSales` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`) ON DELETE CASCADE
)



