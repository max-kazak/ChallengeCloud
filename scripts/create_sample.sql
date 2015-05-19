INSERT INTO challenger.users
VALUES ("d6fbf948694caf35", "volterr", "max kazakov", "$2a$10$rdHCgQDkMqQSk9IK8rhsVu1XhC/Y/VA5ohsMgGtjasABH7b.WAWzi",
        "volterr@gmail.com", 7);
INSERT INTO challenger.users
VALUES ("d8fbf948694caf35", "Yefim", "yefim krokhin", "$2a$10$rdHCgQDkMqQSk9IK8rhsVu1XhC/Y/VA5ohsMgGtjasABH7b.WAWzi",
        "cococo@gmail.com", 1);
INSERT INTO challenger.users VALUES ('19eda4a69b254c61', 'CCloudTest2', 'Bot', NULL, NULL, '1');

/* By Vladimir Zhdanov 01.03.2015 */
-- just to insert some picture try to change path depending on your own PC
-- Nipel-Crumple 12.03.2015
INSERT INTO challenger.images VALUES ("k9f4976e1281a910", "sport", "2015-03-12", "S", LOAD_FILE('D:\\sport.png'));
INSERT INTO challenger.images VALUES ("5f7f505e8099a263", "bicycle", "2015-03-28", "S", LOAD_FILE('D:\\bicycle.png'));
/* By Vladimir Zhdanov on 28.03.2015 */
INSERT INTO challenger.images VALUES ("dadsf87a823e98d2", "mrtwitter", "2015-04-25", "S", LOAD_FILE('D:\\mrtwitter.png'));
/*Nipel-Crumple 18.04.15 for achievements*/
INSERT INTO challenger.images VALUES ("a078b67302203463", "health", "2015-04-19", "S", LOAD_FILE('D:\\heart.png'));
INSERT INTO challenger.images VALUES ("e1235e4da17459ad", "running", "2015-01-29", "S", LOAD_FILE('D:\\running.png'));
INSERT INTO challenger.images VALUES ("5440d562099d1653", "walking", "2015-02-24", "S", LOAD_FILE('D:\\walking.png'));
insert into challenger.images values ("5b65dda05d8fee5a", "completed", CURDATE(), 'S', LOAD_FILE('D:\\completed.png'));
INSERT INTO challenger.images VALUES ("658d7846dc50e503", "photos", "2015-04-29", "S", LOAD_FILE('D:\\photos.png'));
INSERT INTO challenger.images VALUES ("98de650cb50fa85e", "java", "2015-04-29", "S", LOAD_FILE('D:\\java.png'));
INSERT INTO challenger.images VALUES ("14db6974d1d5742d", "book", "2015-04-29", "S", LOAD_FILE('D:\\book.png'));
INSERT INTO challenger.images VALUES ("fc64437bbe45e023", "mrreader", "2015-04-11", "S", LOAD_FILE('D:\\mrreader.png'));
/* By Vladimir Zhdanov on 19.04.2015 */
INSERT INTO challenger.challengegroups VALUES ("5d2c117e674b39de", "Sport", "k9f4976e1281a910");
INSERT INTO challenger.challengegroups VALUES ("20eec2f9234e010a", "Health", "a078b67302203463");
INSERT INTO challenger.challengegroups VALUES ("39960351211b2ca5", "Art", "658d7846dc50e503");
INSERT INTO challenger.challengegroups VALUES ("acdf424d6258f5bb", "Study", "98de650cb50fa85e");

INSERT INTO challenger.challenges VALUES
  ("876aee6e27926e59", "Run about 2 km a day", "You have to run about 2 km a day without any breaks", 3, "CCRunning2km", "5d2c117e674b39de",
   "e1235e4da17459ad");
/* Added ImageID by Vladimir Zhdanov on 18.03.2015 */
INSERT INTO challenger.challenges VALUES 
  ("1a8af31c496601eb", "Riding your bicycle to work", 
  	"Travel to your work by bicycle for 5 days", 3, "CCBicycle", "20eec2f9234e010a", "5f7f505e8099a263");
INSERT INTO challenger.challenges VALUES
  ('7362914e9fade238', '3000 steps every day', 
  	'You have to step at least 3000 steps every day', 3, 'CCSteps3000', '5d2c117e674b39de','5440d562099d1653');
INSERT INTO challenger.challenges VALUES ("0782fea3e2579887","Take 5 photos a day", 
  	"You should take 5 photos a day during the next week", 5, "CCPhotos", "39960351211b2ca5","658d7846dc50e503");
INSERT INTO challenger.challenges VALUES ("cf4a517311bde16e","Programm in Java", 
	"You must programm everyday at least 100 lines of code", 7, "CCJava", "acdf424d6258f5bb","98de650cb50fa85e");
INSERT INTO challenger.challenges VALUES ("9a7cbe0c69620843","Read one book a week", 
	"You must read one book every week", 4, "CCReading", "acdf424d6258f5bb","14db6974d1d5742d");



INSERT INTO challenger.subscriptions VALUES ("11", "d6fbf948694caf35", "876aee6e27926e59", CURDATE());
INSERT INTO challenger.subscriptions VALUES ("12", "d6fbf948694caf35", "876aee6e27926e59", CURDATE());
INSERT INTO challenger.subscriptions VALUES ("13", "d8fbf948694caf35", "876aee6e27926e59", CURDATE());
INSERT INTO challenger.subscriptions VALUES ('16', '19eda4a69b254c61', '7362914e9fade238', '2015-04-01');

/*By Yefim*/
INSERT INTO challenger.usersettings VALUES ("d6fbf948694caf35", "English");
INSERT INTO challenger.posts VALUES ("577231985401290754", "twit_id", "13", CURDATE());
INSERT INTO challenger.posts VALUES ("577232167299858433", "twit_id", "13", CURDATE());

/*Nipel-Crumple for event <---> achievement*/
INSERT INTO challenger.history VALUES ('84d4e0fd55e6315e', 'd8fbf948694caf35', '2008-01-01 00:00:01', '1', '577231985401290754');
INSERT INTO challenger.history VALUES ('4f140f81bbad8692', 'd8fbf948694caf35', '2007-01-01 00:00:01', '1', '577232167299858433');

/* For adding posts by Vladimir Zhdanov */
INSERT INTO challenger.UserConnection VALUES
  ('19eda4a69b254c61', 'twitter', '3105816243', '1', '@CCloudTest2', 'http://twitter.com/CCloudTest2',
   'http://pbs.twimg.com/profile_images/579757517124694016/zmeih_NF_normal.jpg',
   '3105816243-Yi9DrwyTCrdskjlbymj8ocGfa2gM1hCazaoZ5yK', 'AFJYjVbxNzriqQiayamWpXCoFcvD5ANu6lRm4TK1UmPuf', NULL, NULL);


/*Nipel-Crumple for achivement system*/
INSERT INTO challenger.badges VALUES
  ("aislfu4odefsrgts", "Mister Twitter", "Congratulation! You've created 5 posts and got 'Mister Twitter' badge!",
   "dadsf87a823e98d2", "2", '{ "twitterPosts":5 }');
INSERT INTO challenger.badges VALUES("c5975b57c81ba656", "Speedy Racer",'Challenge "Riding your bicycle to work" was completed!', "5b65dda05d8fee5a", "4", '{"challenge_id":"1a8af31c496601eb", "posts":3}');
INSERT INTO challenger.badges VALUES("42ad31bea9ddba9b", "Stepper", 'Challenge "3000 steps every day" was completed!', "5b65dda05d8fee5a", "4", '{"challenge_id":"7362914e9fade238", "posts":3}');
INSERT INTO challenger.badges VALUES("6f2d68c1db840f05", "Quicksilver", 'Challenge "Run about 2 km a day" was completed!', "5b65dda05d8fee5a", "4", '{"challenge_id":"876aee6e27926e59", "posts":3}');
INSERT INTO challenger.badges VALUES ("eae10de4997ee2a7", "Mr Reader", "Challenge 'Read one book a week' was completed!", "5b65dda05d8fee5a", "4", '{"challenge_id":"9a7cbe0c69620843", "posts":4}');
INSERT INTO challenger.badges VALUES ("5854ee1ccbf90ef9", "Photographer", "Challenge 'Take 5 photos a day' was completed!", "5b65dda05d8fee5a", "4", '{"challenge_id":"0782fea3e2579887", "posts":5}');
INSERT INTO challenger.badges VALUES ("334fc14089c85b14", "Java Porgrammer", "Challenge 'Programm in Java' was completed!", "5b65dda05d8fee5a", "4", '{"challenge_id":"cf4a517311bde16e", "posts":7}');



-- creating sample for presentation
INSERT INTO challenger.users VALUES ("d4dc72b420e03abd", "Crisburgher", "John Crisburgh", 
"$2a$10$TnlmWdSe9gd68d8WfJ9iRu39jAEbVn1aumgc0exY/d/HqlbFOMpXC", "thesoftparade@mail.ru", "1");
INSERT INTO challenger.subscriptions VALUES ("416106ac571ac02c", "d4dc72b420e03abd","9a7cbe0c69620843", "2015-04-29");
INSERT INTO challenger.subscriptions VALUES ("7b88021d358e62a0", "d4dc72b420e03abd","cf4a517311bde16e", "2015-05-19");
INSERT INTO challenger.subscriptions VALUES ("9f9f5e9e6b975255", "d4dc72b420e03abd","7362914e9fade238", "2015-05-13");
COMMIT;