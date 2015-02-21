create schema if not exists challenger;

drop table `challenger`.`challenge`;
create table `challenger`.`challenge` (
  `CHALLENGE_ID` VARCHAR(16) NOT NULL,
  `TITLE` VARCHAR(25) NOT NULL,
  `DESCRIPTION` VARCHAR(200) NOT NULL,
  `DIFFICULTY` TINYINT (2) NOT NULL,#10 of 10, f.e.
  `HASHTAG` VARCHAR(200),
  `GROUP_ID` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`CHALLENGE_ID`) USING BTREE
);

drop table `challenger`.`challengegroup`;
create table `challenger`.`challengegroup` (
  `GROUP_ID` VARCHAR(16) NOT NULL,
  `NAME` VARCHAR(25) NOT NULL,
  `ICON` BINARY(200),
  PRIMARY KEY (`GROUP_ID`) USING BTREE
);