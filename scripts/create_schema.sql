CREATE SCHEMA IF NOT EXISTS challenger;

/* Firstly drop all tebles. By Vladimir Zhdanov */
DROP TABLE IF EXISTS `challenger`.`posts`;
DROP TABLE IF EXISTS `challenger`.`subscriptions`;
DROP TABLE IF EXISTS `challenger`.`challenges`;
DROP TABLE IF EXISTS `challenger`.`challengegroups`;
DROP TABLE IF EXISTS `challenger`.`images`;
DROP TABLE IF EXISTS `challenger`.`origins`;
DROP TABLE IF EXISTS `challenger`.`UserConnection`;
DROP TABLE IF EXISTS `challenger`.`usersettings`;
DROP TABLE IF EXISTS `challenger`.`users`;

CREATE TABLE `challenger`.`images` (
	`IMAGE_ID` VARCHAR(16) NOT NULL,
	`NAME` VARCHAR(20),
	`DATE` DATE NOT NULL,
	`SIZE` CHAR(1),
	`IMAGE` MEDIUMBLOB NOT NULL,
	PRIMARY KEY (`IMAGE_ID`) USING BTREE
);

CREATE TABLE `challenger`.`users` (
  `USER_ID` VARCHAR(16) NOT NULL,
  `LOGIN`   VARCHAR(20) NOT NULL,
  `NAME`    VARCHAR(20) NOT NULL,
  `PASS`    VARCHAR(60),
  `EMAIL`   VARCHAR(20),
  `ROLES`   INT(3)      NOT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE,
  UNIQUE KEY `UNI_USER_EMAIL` (`EMAIL`) USING BTREE,
  UNIQUE KEY `UNI_USER_LOGIN` (`NAME`) USING BTREE
);

CREATE TABLE `challenger`.`usersettings` (
  `USER_ID`   VARCHAR(16) NOT NULL,
  `USER_LANG` VARCHAR(16),
  FOREIGN KEY (`USER_ID`) REFERENCES challenger.users (USER_ID),
  PRIMARY KEY (`USER_ID`)
);

CREATE TABLE `challenger`.`UserConnection`
(
  userId         VARCHAR(255) NOT NULL,
  providerId     VARCHAR(255) NOT NULL,
  providerUserId VARCHAR(255),
  rank           INT          NOT NULL,
  displayName    VARCHAR(255),
  profileUrl     VARCHAR(512),
  imageUrl       VARCHAR(512),
  accessToken    VARCHAR(255) NOT NULL,
  secret         VARCHAR(255),
  refreshToken   VARCHAR(255),
  expireTime     LONG,
  PRIMARY KEY (userId, providerId, providerUserId)
);

CREATE UNIQUE INDEX UserConnectionRank ON `challenger`.`UserConnection`
(userId, providerId, rank);

CREATE TABLE `challenger`.`origins` (
  `ORIGIN_ID` VARCHAR(16) NOT NULL,
  `NAME`      VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ORIGIN_ID`) USING BTREE
);

/* Creating table of challenge groups by Vladimir Zhdanov */
CREATE TABLE `challenger`.`challengegroups` (
  `GROUP_ID` VARCHAR(16) NOT NULL,
  `NAME`     VARCHAR(25) NOT NULL,
  `IMAGE_ID` VARCHAR(16),
  PRIMARY KEY (`GROUP_ID`) USING BTREE,
  FOREIGN KEY (`IMAGE_ID`) REFERENCES `challenger`.`images` (`IMAGE_ID`)
);

/* Creating table of challenges by Vladimir Zhdanov */
CREATE TABLE `challenger`.`challenges` (
  `CHALLENGE_ID` VARCHAR(16)  NOT NULL,
  `TITLE`        VARCHAR(25)  NOT NULL,
  `DESCRIPTION`  VARCHAR(200) NOT NULL,
  `DIFFICULTY`   TINYINT(2)   NOT NULL, #10 of 10, f.e.
  `HASHTAG`      VARCHAR(200),
  `GROUP_ID`     VARCHAR(16)  NOT NULL,
  PRIMARY KEY (`CHALLENGE_ID`) USING BTREE,
  FOREIGN KEY (`GROUP_ID`) REFERENCES `challenger`.`challengegroups` (`GROUP_ID`)
);

/* Creating table of subscriptions */
CREATE TABLE `challenger`.`subscriptions` (
  `SUBSCRIPTION_ID` VARCHAR(16) NOT NULL,
  `USER_ID`         VARCHAR(16) NOT NULL,
  `CHALLENGE_ID`    VARCHAR(16) NOT NULL,
  `DATE`            DATE        NOT NULL,
  PRIMARY KEY (`SUBSCRIPTION_ID`),
  FOREIGN KEY (`USER_ID`) REFERENCES `challenger`.`users` (`USER_ID`),
  FOREIGN KEY (`CHALLENGE_ID`) REFERENCES `challenger`.`challenges` (`CHALLENGE_ID`)
);

/*Creating table of posts. By Andrey */
CREATE TABLE `challenger`.`posts` (
  `POST_ID`         VARCHAR(16)  NOT NULL,
  `ORIGIN_ID`       VARCHAR(16)  NOT NULL,
  `SUBSCRIPTION_ID` VARCHAR(16)  NOT NULL,
  `DATE`            TIMESTAMP    NOT NULL,
  `POST_URL`        VARCHAR(512) NOT NULL,
  PRIMARY KEY (`POST_ID`) USING BTREE,
  FOREIGN KEY (`ORIGIN_ID`) REFERENCES challenger.origins (`ORIGIN_ID`),
  FOREIGN KEY (`SUBSCRIPTION_ID`) REFERENCES challenger.subscriptions (`SUBSCRIPTION_ID`)
);

commit;

