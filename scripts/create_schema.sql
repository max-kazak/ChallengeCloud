CREATE SCHEMA IF NOT EXISTS challenger;

DROP TABLE IF EXISTS `challenger`.`history`;
DROP TABLE IF EXISTS `challenger`.`posts`;
DROP TABLE IF EXISTS `challenger`.`subscriptions`;
DROP TABLE IF EXISTS `challenger`.`challenges`;
DROP TABLE IF EXISTS `challenger`.`challengegroups`;
DROP TABLE IF EXISTS `challenger`.`badges`;
DROP TABLE IF EXISTS `challenger`.`images`;
DROP TABLE IF EXISTS `challenger`.`origins`;
DROP TABLE IF EXISTS `challenger`.`UserConnection`;
DROP TABLE IF EXISTS `challenger`.`usersettings`;
DROP TABLE IF EXISTS `challenger`.`events`;
DROP TABLE IF EXISTS `challenger`.`users`;

CREATE TABLE `challenger`.`events` (
  `EVENT_ID` VARCHAR(16)  NOT NULL,
  `NAME`     VARCHAR(40)  NOT NULL,
  `CLASS`    VARCHAR(100) NOT NULL,
  PRIMARY KEY (`EVENT_ID`) USING BTREE
);

INSERT INTO challenger.events VALUES ('1', 'CCEvent', 'com.codegroup.challengecloud.events.CCEvent');
INSERT INTO challenger.events VALUES ('2', "TwitterPostEvent", "com.codegroup.challengecloud.events.TwitterPostEvent");
insert into challenger.events values ("3", "AchievementEvent","com.codegroup.challengecloud.events.AchievementEvent");
insert into challenger.events values ("4", "ChallengeCompletedEvent", "com.codegroup.challengecloud.events.ChallengeCompletedEvent");

CREATE TABLE `challenger`.`images` (
  `IMAGE_ID` VARCHAR(16) NOT NULL,
  `NAME`     VARCHAR(20),
  `DATE`     DATE        NOT NULL,
  `SIZE`     CHAR(1),
  `DATA`     MEDIUMBLOB  NOT NULL,
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
  UNIQUE KEY `UNI_USER_LOGIN` (`LOGIN`) USING BTREE
);

/*By Yefim Krokhin on 19.04.2015*/
CREATE TABLE `challenger`.`history` (
  `HISTORY_ID` VARCHAR(16) NOT NULL,
  `USER_ID`  VARCHAR(16) NOT NULL,
  `TIME`     TIMESTAMP,
  `EVENT_ID` VARCHAR(16) NOT NULL,
  `REF_ID`   VARCHAR(50) NOT NULL,
  PRIMARY KEY (`HISTORY_ID`) USING BTREE,
  FOREIGN KEY (`USER_ID`) REFERENCES challenger.users (`USER_ID`),
  FOREIGN KEY (`EVENT_ID`) REFERENCES challenger.events (`EVENT_ID`)
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

INSERT INTO challenger.origins VALUES ("twit_id", "Twitter");

/* Creating table of challenge groups by Vladimir Zhdanov */
CREATE TABLE `challenger`.`challengegroups` (
  `GROUP_ID` VARCHAR(16) NOT NULL,
  `NAME`     VARCHAR(25) NOT NULL,
  `IMAGE_ID` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`GROUP_ID`) USING BTREE,
  FOREIGN KEY (`IMAGE_ID`) REFERENCES `challenger`.`images` (`IMAGE_ID`)
);

/* Creating table of challenges by Vladimir Zhdanov */
CREATE TABLE `challenger`.`challenges` (
  `CHALLENGE_ID` VARCHAR(16)  NOT NULL,
  `TITLE`        VARCHAR(25)  NOT NULL,
  `DESCRIPTION`  VARCHAR(200) NOT NULL,
  `DIFFICULTY`   TINYINT(2)   NOT NULL, #10 of 10, f.e.
  `HASHTAG`      VARCHAR(100),
  `GROUP_ID`     VARCHAR(16)  NOT NULL,
  `IMAGE_ID`     VARCHAR(16)  NOT NULL, /* By Vladimir Zhdanov on 28.03.2015 */
  PRIMARY KEY (`CHALLENGE_ID`) USING BTREE,
  FOREIGN KEY (`GROUP_ID`) REFERENCES `challenger`.`challengegroups` (`GROUP_ID`),
  FOREIGN KEY (`IMAGE_ID`) REFERENCES `challenger`.`images` (`IMAGE_ID`)
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
/*Remake by Yefim*/
CREATE TABLE `challenger`.`posts` (
  `POST_ID`         VARCHAR(50) NOT NULL,
  `ORIGIN_ID`       VARCHAR(16) NOT NULL,
  `SUBSCRIPTION_ID` VARCHAR(16) NOT NULL,
  `DATE`            DATE        NOT NULL,
  PRIMARY KEY (`POST_ID`, `ORIGIN_ID`) USING BTREE,
  FOREIGN KEY (`ORIGIN_ID`) REFERENCES challenger.origins (`ORIGIN_ID`),
  FOREIGN KEY (`SUBSCRIPTION_ID`) REFERENCES challenger.subscriptions (`SUBSCRIPTION_ID`)
);

/*Nipel-Crumple for achievement system*/
CREATE TABLE `challenger`.`badges` (
  `BADGE_ID`    VARCHAR(16)  NOT NULL,
  `NAME`        VARCHAR(40)  NOT NULL,
  `DESCRIPTION` VARCHAR(150) NOT NULL,
  `IMAGE_ID`    VARCHAR(16)  NOT NULL,
  `EVENT_ID`    VARCHAR(16)  NOT NULL,
  `CONDITION`   VARCHAR(80)  NOT NULL,
  PRIMARY KEY (`BADGE_ID`) USING BTREE,
  FOREIGN KEY (`IMAGE_ID`) REFERENCES `challenger`.`images` (`IMAGE_ID`),
  FOREIGN KEY (`EVENT_ID`) REFERENCES `challenger`.`events` (`EVENT_ID`)
);

COMMIT;

