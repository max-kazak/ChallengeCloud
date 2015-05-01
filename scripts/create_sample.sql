/* Insert test data */
INSERT INTO challenger.users
VALUES ("d6fbf948694caf35", "volterr", "max kazakov", "$2a$10$rdHCgQDkMqQSk9IK8rhsVu1XhC/Y/VA5ohsMgGtjasABH7b.WAWzi",
        "volterr@gmail.com", 7);
INSERT INTO challenger.users
VALUES ("d8fbf948694caf35", "Yefim", "yefim krokhin", "$2a$10$rdHCgQDkMqQSk9IK8rhsVu1XhC/Y/VA5ohsMgGtjasABH7b.WAWzi",
        "cococo@gmail.com", 1);


/* By Vladimir Zhdanov 01.03.2015 */
-- just to insert some picture try to change path depending on your own PC
-- Nipel-Crumple 12.03.2015
INSERT INTO `challenger`.`images` VALUES ("123123", "Image1", "2015-03-12", "S", LOAD_FILE('D:\#1.png'));
INSERT INTO `challenger`.`images`
VALUES ("5f7f505e8099a263", "Bicycle", "2015-03-28", "S", LOAD_FILE('D:\Bicycle.png'));
/* By Vladimir Zhdanov on 28.03.2015 */
INSERT INTO challenger.images VALUES ("dadsf87a823e98d2", "MrTwitter", CURDATE(), 'M', LOAD_FILE("D:\mrtwitter.png"));
/*Nipel-Crumple 18.04.15 for achivements*/
INSERT INTO `challenger`.`images`
VALUES ("a078b67302203463", "Health Group", "2015-04-19", "S", LOAD_FILE('D:\Heart.png'));
/* By Vladimir Zhdanov on 19.04.2015 */
INSERT INTO challenger.challengegroups VALUES ("5d2c117e674b39de", "Challenge group 1 name", "123123");
INSERT INTO challenger.challengegroups VALUES ("20eec2f9234e010a", "Health", "a078b67302203463");
INSERT INTO challenger.challenges VALUES
  ("876aee6e27926e59", "Challenge 1 title", "Challenge 1  decription", 20, "Challenge 1 hash tag", "5d2c117e674b39de",
   "5f7f505e8099a263");
/* Added ImageID by Vladimir Zhdanov on 18.03.2015 */
INSERT INTO challenger.challenges VALUES
  ("1a8af31c496601eb", "To Work By Bicycle", "Travel to your work by bicycle for 5 days", 8, "CCBicycle",
   "20eec2f9234e010a", "5f7f505e8099a263");
INSERT INTO challenger.subscriptions VALUES ("11", "d6fbf948694caf35", "876aee6e27926e59", CURDATE());
INSERT INTO challenger.subscriptions VALUES ("12", "d6fbf948694caf35", "876aee6e27926e59", CURDATE());

/*By Yefim*/
INSERT INTO challenger.usersettings VALUES ("d6fbf948694caf35", "English");
INSERT INTO challenger.subscriptions VALUES ("13", "d8fbf948694caf35", "876aee6e27926e59", CURDATE());
INSERT INTO challenger.subscriptions VALUES ("14", "d8fbf948694caf35", "876aee6e27926e59", CURDATE());
INSERT INTO challenger.posts VALUES ("577231985401290754", "twit_id", "13", CURDATE());
INSERT INTO challenger.posts VALUES ("577232167299858433", "twit_id", "13", CURDATE());

/*Nipel-Crumple for event <---> achievement*/
INSERT INTO challenger.history VALUES ('123', 'd8fbf948694caf35', '2008-01-01 00:00:01', '1', '577231985401290754');
INSERT INTO challenger.history VALUES ('12', 'd8fbf948694caf35', '2007-01-01 00:00:01', '1', '577232167299858433');

/* For adding posts by Vladimir Zhdanov */
INSERT INTO challenger.users VALUES ('19eda4a69b254c61', 'CCloudTest2', 'Bot', NULL, NULL, '1');
INSERT INTO challenger.UserConnection VALUES
  ('19eda4a69b254c61', 'twitter', '3105816243', '1', '@CCloudTest2', 'http://twitter.com/CCloudTest2',
   'http://pbs.twimg.com/profile_images/579757517124694016/zmeih_NF_normal.jpg',
   '3105816243-Yi9DrwyTCrdskjlbymj8ocGfa2gM1hCazaoZ5yK', 'AFJYjVbxNzriqQiayamWpXCoFcvD5ANu6lRm4TK1UmPuf', NULL, NULL);
INSERT INTO challenger.challenges VALUES
  ('7362914e9fade238', 'Challenge2title', 'Challenge2Description', 30, 'Challenge2', '5d2c117e674b39de',
   '5f7f505e8099a263');
INSERT INTO challenger.subscriptions VALUES ('16', '19eda4a69b254c61', '7362914e9fade238', '2015-04-01');

/*Nipel-Crumple for achivement system*/
INSERT INTO challenger.badges VALUES
  ("aislfu4odefsrgts", "Mister Twitter", "Congratulation! You've created 5 posts and got 'Mister Twitter' badge!",
   "dadsf87a823e98d2", "2", "{ \"twitterPosts\":5 }");

COMMIT;