create schema if not exists challenger;

drop table `challenger`.`users`;
create table `challenger`.`users` (
  `USER_ID` VARCHAR(16) NOT NULL,
  `LOGIN` VARCHAR(20) NOT NULL,
  `PASS` varchar(60) NOT NULL,
  `EMAIL` varchar(20) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `ROLES` int(3) NOT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE,
  UNIQUE KEY `UNI_USER_EMAIL` (`EMAIL`) USING BTREE,
  UNIQUE KEY `UNI_USER_LOGIN` (`LOGIN`) USING BTREE
);