create schema if not exists challenger;

drop table `challenger`.`users`;
create table `challenger`.`users` (
  `USER_ID` VARCHAR(16) NOT NULL,
  `LOGIN` VARCHAR(20) NOT NULL,
  `PASS` varchar(60),
  `EMAIL` varchar(20),
  `NAME` varchar(20) NOT NULL,
  `ROLES` int(3) NOT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE,
  UNIQUE KEY `UNI_USER_EMAIL` (`EMAIL`) USING BTREE,
  UNIQUE KEY `UNI_USER_LOGIN` (`LOGIN`) USING BTREE
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

drop table `challenger`.`posts`;
create table `challenger`.`posts` (
  `POST_ID` VARCHAR(16) NOT NULL,
  `ORIGIN_ID` VARCHAR(16) NOT NULL
  `SUBSRIPTION_ID` VARCHAR(16) NOT NULL,
  `DATE` TIMESTAMP NOT NULL,
  `POST_URL` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`POST_ID`) USING BTREE,
);

/* Creating table of subscriptions */
DROP TABLE `challenger`.`subscriptions`;

CREATE TABLE `challenger`.`subscribtions`(
  `SUB_ID` VARCHAR(16) NOT NULL,
  `USER_ID` VARCHAR(16) NOT NULL,
  `CHALLENGE_ID` VARCHAR(16) NOT NULL,
  `DATE` DATE NOT NULL,
CONSTRAINT pk_SubID PRIMARY KEY (`SUB_ID`, `DATE`),
FOREIGN KEY (`USER_ID`) 
  REFERENCES `challenger`.`users`(`USER_ID`),
FOREIGN KEY (`CHALLENGE_ID`) 
  REFERENCES `challenger`.`challenges`(`CHALLENGE_ID`)
);