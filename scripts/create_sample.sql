/* Insert test data */
insert into challenger.users
values ("d6fbf948694caf35", "volterr", "max kazakov", "$2a$10$rdHCgQDkMqQSk9IK8rhsVu1XhC/Y/VA5ohsMgGtjasABH7b.WAWzi", "volterr@gmail.com", 7);

/*By Yefim*/
insert into challenger.usersettings values ("d6fbf948694caf35",   "English");
insert into challenger.origins values ("twit_id", "Twitter");

/* By Vladimir Zhdanov 01.03.2015 */
insert into challenger.challengegroups values ("5d2c117e674b39de", "Challenge group 1 name", "Challenge group 1 icon path");
insert into challenger.challengegroups values ("ee637271e976b5e9", "Challenge group 2 name", "Challenge group 2 icon path");
insert into challenger.challenges values ("876aee6e27926e59", "Challenge 1 title", "Challenge 1  decription", 0, "Challenge 1 hash tag", "5d2c117e674b39de");
insert into challenger.challenges values ("dc37881d79d5be60", "Challenge 2 title", "Challenge 2  decription", 1, "Challenge 2 hash tag", "5d2c117e674b39de");
insert into challenger.challenges values ("8de11b5cf5d6161d", "Challenge 3 title", "Challenge 3  decription", 2, "Challenge 3 hash tag", "ee637271e976b5e9");
insert into challenger.challenges values ("cd46e396592d2d7f", "Challenge 4 title", "Challenge 4  decription", 3, "Challenge 4 hash tag", "ee637271e976b5e9");
insert into challenger.challenges values ("85f0d7322e8c63ea", "Challenge 5 title", "Challenge 5  decription", 4, "Challenge 5 hash tag", "ee637271e976b5e9");
insert into challenger.challenges values ("84b42c09142445f6", "Challenge 6 title", "Challenge 6  decription", 5, "Challenge 6 hash tag", "ee637271e976b5e9");

insert into challenger.subscriptions values ("11", "d6fbf948694caf35", "876aee6e27926e59", CURDATE());


commit;