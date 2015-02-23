create schema if not exists challenger;

drop table `challenger`.`users`;
create table `challenger`.`users` (
  `USER_ID` VARCHAR(16) NOT NULL,
  `NAME` VARCHAR(20) NOT NULL,
  `PASS` varchar(60),
  `EMAIL` varchar(20),
  `ROLES` int(3) NOT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE,
  UNIQUE KEY `UNI_USER_EMAIL` (`EMAIL`) USING BTREE,
  UNIQUE KEY `UNI_USER_LOGIN` (`NAME`) USING BTREE
);
drop table `challenger`.`usersettings`;
CREATE TABLE `challenger`.`usersettings` (
  `USER_ID` VARCHAR(16) NOT NULL,
  `USER_LANG` VARCHAR(16),
  FOREIGN KEY (`USER_ID`) REFERENCES challenger.users(USER_ID),
  PRIMARY KEY (`USER_ID`)
);

drop table `challenger`.`UserConnection`;
CREATE TABLE `challenger`.`UserConnection`
(
  userId varchar(255) NOT NULL,
  providerId varchar(255) NOT NULL,
  providerUserId varchar(255),
  rank int not null,
  displayName varchar(255),
  profileUrl varchar(512),
  imageUrl varchar(512),
  accessToken varchar(255) NOT NULL,
  secret varchar(255),
  refreshToken varchar(255),
  expireTime long,
  primary key (userId, providerId, providerUserId)
);

CREATE UNIQUE INDEX UserConnectionRank ON UserConnection
(userId, providerId, rank);

drop table `challenger`.`origins`;
CREATE TABLE `challenger`.`origins` (
  `ORIGIN_ID` VARCHAR(16) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`ORIGIN_ID`) USING BTREE
);

drop table `challenger`.`posts`;
create table `challenger`.`posts` (
  `POST_ID` VARCHAR(16) NOT NULL,
  `ORIGIN_ID` VARCHAR(16) NOT NULL,
  `SUBSCRIPTION_ID` VARCHAR(16) NOT NULL,
  `DATE` TIMESTAMP NOT NULL,
  `POST_URL` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`POST_ID`) USING BTREE,
  FOREIGN KEY (`ORIGIN_ID`) REFERENCES challenger.origins(`ORIGIN_ID`)
);

drop table `challenger`.`challengegroups`;
create table `challenger`.`challengegroups` (
  `GROUP_ID` VARCHAR(16) NOT NULL,
  `NAME` VARCHAR(25) NOT NULL,
  `ICON` BINARY(200),
  PRIMARY KEY (`GROUP_ID`) USING BTREE
);

drop table `challenger`.`challenges`;
create table `challenger`.`challenges` (
  `CHALLENGE_ID` VARCHAR(16) NOT NULL,
  `TITLE` VARCHAR(25) NOT NULL,
  `DESCRIPTION` VARCHAR(200) NOT NULL,
  `DIFFICULTY` TINYINT (2) NOT NULL,#10 of 10, f.e.
  `HASHTAG` VARCHAR(200),
  `GROUP_ID` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`CHALLENGE_ID`) USING BTREE,
  FOREIGN KEY (`GROUP_ID`)
  REFERENCES `challenger`.`challengegroups`(`GROUP_ID`)
);

/* Creating table of subscriptions */
DROP TABLE `challenger`.`subscriptions`;

CREATE TABLE `challenger`.`subscriptions`(
  `SUBSCRIPTION_ID` VARCHAR(16) NOT NULL,
  `USER_ID` VARCHAR(16) NOT NULL,
  `CHALLENGE_ID` VARCHAR(16) NOT NULL,
  `DATE` DATE NOT NULL,
  PRIMARY KEY (`SUBSCRIPTION_ID`),
  FOREIGN KEY (`USER_ID`)
  REFERENCES `challenger`.`users`(`USER_ID`),
  FOREIGN KEY (`CHALLENGE_ID`)
  REFERENCES `challenger`.`challenges`(`CHALLENGE_ID`)
);
